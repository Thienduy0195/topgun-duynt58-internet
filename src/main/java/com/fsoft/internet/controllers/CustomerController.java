package com.fsoft.internet.controllers;

import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fsoft.internet.dto.CustomerDTO;
import com.fsoft.internet.entities.Customer;
import com.fsoft.internet.entities.Payment;
import com.fsoft.internet.entities.Records;
import com.fsoft.internet.services.customer.ICustomerService;
import com.fsoft.internet.services.payment.IPaymentService;
import com.fsoft.internet.services.record.IRecordService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

  @Autowired
  private IRecordService recordService;

  @Autowired
  private IPaymentService paymentService;

  @Autowired
  private ICustomerService customerService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("customer", new CustomerDTO());
    return "customer/create-customer";
  }


@PostMapping("/create")
  public String createNew(
      @Valid @ModelAttribute("customer") CustomerDTO customerDTO,
      BindingResult bindingResult, Model model,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      List<FieldError> errors = bindingResult.getFieldErrors();
      Map<String, String> errorList = new LinkedHashMap<>();
      for (FieldError item : errors) {
        String field = item.getField();
        String msg = item.getDefaultMessage();
        errorList.put(field, msg);
      }
//      model.addAttribute("errorList", errorList);
      model.addAttribute("customer", customerDTO);
      return "customer/create-customer";
    }

    model.addAttribute("message", "Add new customer successfully!");
    Customer customer = modelMapper.map(customerDTO, Customer.class);
    customerService.createOrUpdate(customer);
    redirectAttributes.addFlashAttribute("message",
        "Create customer successfully!");
    return "forward:/customer/list";
  }

  @GetMapping("/list")
  public String showList(Model model,
      @RequestParam(name = "page", defaultValue = "0") int page) {
    int noOfRecords = customerService.getNoOfRecords();
    int recordsPerPage = 5;
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    if (page < 1) {
      page = 1;
    } else if (page > noOfRecords) {
      page = noOfRecords;
    }
    int start = page > 0 ? page - 1 : 0;
    List<Customer> customers = customerService
        .getRecordsForCurrentPage((start) * recordsPerPage, recordsPerPage);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page);
    model.addAttribute("customers", customers);
    return "customer/customer-list";
  }

  @GetMapping("/search")
  public String search(
      @RequestParam(value = "searchName", required = true) String searchName,
      @RequestParam(value = "searchAddress", required = true) String searchAddress,
      @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
    try {
      int start = page > 0 ? page - 1 : 0;
      int recordsPerPage = 5;
      int noOfRecords = customerService
          .searchByNameAndAddress(searchName, searchAddress).size();
      List<Customer> customers = customerService.searchByNameAndAddress(
          searchName, searchAddress, (start) * recordsPerPage, recordsPerPage);
      int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
      if (page < 1) {
        page = 2;
      } else if (page > noOfPages) {
        page = noOfPages - 1;
      }
      model.addAttribute("searchName", searchName);
      model.addAttribute("searchAddress", searchAddress);
      model.addAttribute("noOfPages", noOfPages);
      model.addAttribute("currentPage", page);
      model.addAttribute("customers", customers);
      return "customer/customer-list";
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "customer/customer-list";
  }

  @GetMapping("/{id}")
  public String showUpdateForm(Model model, @PathVariable("id") String id) {
    Optional<Customer> customer = customerService.findById(id);
    if (customer.isEmpty()) {
      return "commons/error-page";
    }
    CustomerDTO customerDTO = new CustomerDTO();
    if (customer.isPresent()) {
      customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
    }
    model.addAttribute("customer", customerDTO);
    return "customer/edit-customer";
  }

  @PostMapping("/{id}")
  public String update(@ModelAttribute("customer") CustomerDTO customerDTO,
      BindingResult bindingResult, Model model, @PathVariable("id") String id,
      RedirectAttributes redirectAttributes) {
    customerDTO.setCustomerId(id);
    customerDTO.validate(customerDTO, bindingResult);

    if (bindingResult.hasErrors()) {
      model.addAttribute("customer", customerDTO);
      return "customer/edit-customer";
    }
    Customer customer = modelMapper.map(customerDTO, Customer.class);
    customerService.createOrUpdate(customer);
    redirectAttributes.addFlashAttribute("message",
        "Update customer successfully!");
    return "redirect:/customer/list";
  }

  @PostMapping("/delete")
  public String deleteCustomer(@RequestParam String customerId, Model model,
      RedirectAttributes redirectAttributes) {
    if (customerService.delete(customerId)) {
      model.addAttribute("message", "Delete successfully");
    }
    redirectAttributes.addFlashAttribute("message",
        "Delete customer successfully!");
    return "redirect:/customer/list";
  }

  @GetMapping("/history/{id}")
  public String showHistory(Model model, @PathVariable("id") String id) {
    Optional<Customer> customer = customerService.findById(id);
    if (customer.isEmpty()) {
      return "commons/error-page";
    }
    List<Records> records = recordService.getAllByCustomerId(id);
    List<Payment> payments = paymentService.getAllByCustomerId(id);
    Double totalDouble = paymentService.caculateTotal(payments);
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    String moneyString = formatter.format(totalDouble);
    model.addAttribute("records", records);
    model.addAttribute("payments", payments);
    model.addAttribute("totalDouble", moneyString);
    return "customer/history";
  }

  @GetMapping("/search/*")
  public String notFound() {
    return "commons/error-page";
  }

}
