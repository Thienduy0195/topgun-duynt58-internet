package com.fsoft.internet.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fsoft.internet.dto.ComputerDTO;
import com.fsoft.internet.entities.Computer;
import com.fsoft.internet.services.computer.IComputerService;

@Controller
@RequestMapping(value = "/computer")
public class ComputerController {

  @Autowired
  IComputerService computerService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("computer", new ComputerDTO());
    return "computer/create-computer";
  }

  @PostMapping("/create")
  public String createNew(
      @Valid @ModelAttribute("computer") ComputerDTO computerDTO,
      BindingResult bindingResult, Model model,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("computer", computerDTO);
      return "computer/create-computer";
    }
    Computer computer = modelMapper.map(computerDTO, Computer.class);
    computerService.createOrUpdate(computer);
    redirectAttributes.addFlashAttribute("message",
        "Add new computer successfully!");
    return "redirect:/computer/list";
  }

  @GetMapping("/list")
  public String showList(Model model,
      @RequestParam(name = "page", defaultValue = "0") int page) {
    int noOfRecords = computerService.getNoOfRecords();
    int recordsPerPage = 5;
    if (page < 1) {
      page = 1;
    } else if (page > noOfRecords) {
      page = noOfRecords;
    }
    int start = page > 0 ? page - 1 : 0;
    List<Computer> computers = computerService
        .getRecordsForCurrentPage((start) * recordsPerPage, recordsPerPage);

    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page > 0 ? page : 1);
    model.addAttribute("computers", computers);
    return "computer/computer-list";
  }

  @GetMapping("/search")
  public String search(
      @RequestParam(value = "searchId", required = true) String searchId,
      @RequestParam(value = "searchLocation", required = true) String searchLocation,
      @RequestParam(value = "status", required = true) String status,
      @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
    int start = page > 0 ? page - 1 : 0;
    int recordsPerPage = 5;
    int noOfRecords = computerService.search(searchId, searchLocation, status)
        .size();
    List<Computer> computers = computerService.search(searchId, searchLocation,
        status, (start) * recordsPerPage, recordsPerPage);

    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    if (page < 1) {
      page = 2;
    } else if (page > noOfRecords) {
      page = noOfRecords - 1;
    }
    model.addAttribute("searchId", searchId);
    model.addAttribute("searchLocation", searchLocation);
    model.addAttribute("status", status);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page);
    model.addAttribute("computers", computers);
    return "computer/computer-list";
  }

  @GetMapping("/{id}")
  public String showUpdateForm(Model model, @PathVariable("id") String id) {
    Optional<Computer> computer = computerService.findById(id);
    ComputerDTO computerDTO = new ComputerDTO();
    if (computer.isPresent()) {
      computerDTO = modelMapper.map(computer.get(), ComputerDTO.class);
    }
    model.addAttribute("computer", computerDTO);
    return "computer/edit-computer";
  }

  @PostMapping("/{id}")
  public String update(@ModelAttribute("computer") ComputerDTO computerDTO,
      BindingResult bindingResult, Model model, @PathVariable("id") String id,
      RedirectAttributes redirectAttributes) {
    computerDTO.validate(computerDTO, bindingResult);
    if (bindingResult.hasErrors()) {
      model.addAttribute("computer", computerDTO);
      return "computer/edit-computer";
    }
    Computer computer = modelMapper.map(computerDTO, Computer.class);
    computer.setComputerId(id);
    computerService.createOrUpdate(computer);

    redirectAttributes.addFlashAttribute("message",
        "Update computer successfully!");
    return "redirect:/computer/list";
  }

  @PostMapping("/delete")
  public String deleteComputer(@RequestParam String computerId, Model model,
      RedirectAttributes redirectAttributes) {
    computerService.delete(computerId);
    redirectAttributes.addFlashAttribute("message",
        "Delete computer successfully!");
    return "redirect:/computer/list";
  }
}
