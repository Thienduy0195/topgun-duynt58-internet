package com.fsoft.internet.controllers;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fsoft.internet.dto.PaymentDTO;
import com.fsoft.internet.entities.Customer;
import com.fsoft.internet.entities.Payment;
import com.fsoft.internet.entities.PaymentId;
import com.fsoft.internet.entities.Product;
import com.fsoft.internet.services.customer.ICustomerService;
import com.fsoft.internet.services.payment.IPaymentService;
import com.fsoft.internet.services.product.IProductService;

@Controller
@RequestMapping(value = "/payment")
public class PaymentController {

  @Autowired
  private ICustomerService customerService;
  @Autowired
  private IProductService productService;

  @Autowired
  private IPaymentService paymentService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("paymentDTO", new PaymentDTO());
    return "payment/create-payment";
  }

  @PostMapping("/create")
  public String createNew(
      @Valid @ModelAttribute("paymentDTO") PaymentDTO paymentDTO,
      BindingResult bindingResult, Model model,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("paymentDTO", paymentDTO);
      return "payment/create-payment";
    }
    Customer customer = customerService.findById(paymentDTO.getCustomerId())
        .get();
    Product product = productService.findById(paymentDTO.getProductId()).get();
    Payment payment = modelMapper.map(paymentDTO, Payment.class);
    PaymentId paymentId = modelMapper.map(paymentDTO, PaymentId.class);
    payment.setPaymentId(paymentId);
    payment.setProduct(product);
    payment.setCustomer(customer);
    System.out.println(payment.toString());
    paymentService.createOrUpdate(payment);

    redirectAttributes.addFlashAttribute("message",
        "Add new payment successfully!");
    return "redirect:/payment/list";
  }

  @GetMapping("/list")
  public String showList(Model model,
      @RequestParam(name = "page", defaultValue = "0") int page) {
    int noOfRecords = paymentService.getNoOfRecords();
    int recordsPerPage = 5;
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    if (page < 1) {
      page = 1;
    } else if (page > noOfRecords) {
      page = noOfRecords;
    }
    int start = page > 0 ? page - 1 : 0;
    List<Payment> payments = paymentService
        .getRecordsForCurrentPage((start) * recordsPerPage, recordsPerPage);
    Double totalDouble = paymentService.caculateTotal(payments);
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
    String moneyString = formatter.format(totalDouble);
    model.addAttribute("totalDouble", moneyString);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page);
    model.addAttribute("payments", payments);
    return "payment/payment-list";
  }

  @GetMapping("/search")
  public String search(
      @RequestParam(value = "customerId", required = true) String customerId,
      @RequestParam(value = "productId", required = true) String productId,
      @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
    int start = page > 0 ? page - 1 : 0;
    int recordsPerPage = 5;
    int noOfRecords = paymentService.search(customerId, productId).size();
    List<Payment> payments = paymentService.search(customerId, productId,
        (start) * recordsPerPage, recordsPerPage);

    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    if (page < 1) {
      page = 1;
    } else if (page > noOfRecords) {
      page = noOfRecords;
    }
    model.addAttribute("customerId", customerId);
    model.addAttribute("productId", productId);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page);
    model.addAttribute("payments", payments);
    return "payment/payment-list";
  }

  @ModelAttribute("products")
  public List<Product> getProducts() {
    return productService.getList();
  }

  @ModelAttribute("customers")
  public List<Customer> getCustomers() {
    return customerService.getList();
  }

}
