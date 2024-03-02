package com.fsoft.internet.controllers;

import java.util.List;

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

import com.fsoft.internet.dto.RecordDTO;
import com.fsoft.internet.entities.Computer;
import com.fsoft.internet.entities.Customer;
import com.fsoft.internet.entities.RecordId;
import com.fsoft.internet.entities.Records;
import com.fsoft.internet.services.computer.IComputerService;
import com.fsoft.internet.services.customer.ICustomerService;
import com.fsoft.internet.services.record.IRecordService;

@Controller
@RequestMapping(value = "/record")
public class RecordController {

  @Autowired
  private ICustomerService customerService;

  @Autowired
  private IComputerService computerService;

  @Autowired
  private IRecordService recordService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("record", new RecordDTO());
    return "record/create-record";
  }

  @PostMapping("/create")
  public String createNew(@Valid @ModelAttribute("record") RecordDTO recordDTO,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("record", recordDTO);
      return "record/create-record";
    }
    Customer customer = customerService.findById(recordDTO.getCustomerId())
        .get();
    Computer computer = computerService.findById(recordDTO.getComputerId())
        .get();

    RecordId recordId = modelMapper.map(recordDTO, RecordId.class);
    Records records = modelMapper.map(recordDTO, Records.class);
    computer.setStatus("Online");
    computerService.createOrUpdate(computer);
    records.setComputer(computer);
    records.setCustomer(customer);
    records.setRecordId(recordId);
    recordService.createOrUpdate(records);
    return "redirect:/record/list";
  }

  @GetMapping("/list")
  public String showList(Model model,
      @RequestParam(name = "page", defaultValue = "0") int page) {
    int noOfRecords = recordService.getNoOfRecords();
    int recordsPerPage = 5;
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    if (page < 1) {
      page = 1;
    } else if (page > noOfRecords) {
      page = noOfRecords;
    }
    int start = page > 0 ? page - 1 : 0;
    List<Records> records = recordService
        .getRecordsForCurrentPage((start) * recordsPerPage, recordsPerPage);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page);
    model.addAttribute("records", records);
    return "record/record-list";
  }

  @GetMapping("/search")
  public String search(
      @RequestParam(value = "customerId", required = true) String customerId,
      @RequestParam(value = "computerId", required = true) String computerId,
      @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
    int start = page > 0 ? page - 1 : 0;
    int recordsPerPage = 5;
    int noOfRecords = recordService.search(customerId, computerId).size();
    List<Records> records = recordService.search(customerId, computerId,
        (start) * recordsPerPage, recordsPerPage);

    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    if (page < 1) {
      page = 1;
    } else if (page > noOfRecords) {
      page = noOfRecords;
    }
    model.addAttribute("customerId", customerId);
    model.addAttribute("computerId", computerId);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page);
    model.addAttribute("records", records);
    return "record/record-list";
  }

  @ModelAttribute("computers")
  public List<Computer> getComputers() {
    return computerService.getOfflineList();
  }

  @ModelAttribute("customers")
  public List<Customer> getCustomers() {
    return customerService.getList();
  }

}
