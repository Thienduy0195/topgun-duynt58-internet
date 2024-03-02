package com.fsoft.internet.controllers;

import java.util.LinkedHashMap;
import java.util.List;
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

import com.fsoft.internet.dto.ProductDTO;
import com.fsoft.internet.entities.Product;
import com.fsoft.internet.services.product.IProductService;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

  @Autowired
  private IProductService productService;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * @author DuyNT58
   * @Author_birth_date: 1995-01-01
   * @param model
   * @return
   * @TODO
   */
  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("product", new ProductDTO());
    return "product/create-product";
  }

  /**
   * @author DuyNT58
   * @Author_birth_date: 1995-01-01
   * @param productDTO
   * @param bindingResult
   * @param model
   * @return
   * @TODO
   */
  @PostMapping("/create")
  public String createNew(
      @Valid @ModelAttribute("product") ProductDTO productDTO,
      BindingResult bindingResult, Model model,
      RedirectAttributes redirectAttributes) {
    productDTO.validate(productDTO, bindingResult);
    if (bindingResult.hasErrors()) {
      System.out.println("ERROR " + bindingResult);
      List<FieldError> errors = bindingResult.getFieldErrors();
      Map<String, String> errorList = new LinkedHashMap<>();
      for (FieldError item : errors) {
        String field = item.getField();
        System.out.println(field.toString());
        String msg = item.getDefaultMessage();
        System.out.println(msg.toString());
        errorList.put(field, msg);
      }
      model.addAttribute("errorList", errorList);
      model.addAttribute("customer", productDTO);
      return "product/create-product";
    }
    System.out.println(productDTO.toString());
    Product product = modelMapper.map(productDTO, Product.class);
    productService.createOrUpdate(product);
    redirectAttributes.addFlashAttribute("message",
        "Add new service successfully!");
    return "redirect:/product/create";
  }

  @GetMapping("/list")
  public String showList(Model model,
      @RequestParam(name = "page", defaultValue = "0") int page) {

    int noOfRecords = productService.getNoOfRecords();
    int recordsPerPage = 6;
    if (page < 1) {
      page = 1;
    } else if (page > noOfRecords) {
      page = noOfRecords;
    }
    int start = page > 0 ? page - 1 : 0;
    List<Product> products = productService
        .getRecordsForCurrentPage((start) * recordsPerPage, recordsPerPage);

    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page > 0 ? page : 1);
    model.addAttribute("products", products);
    return "product/product-list";
  }

  @GetMapping("/search")
  public String search(
      @RequestParam(value = "searchName", required = true) String searchName,
      @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
    int start = page > 0 ? page - 1 : 0;
    int recordsPerPage = 5;
    List<Product> products = productService.searchByName(searchName,
        (start) * recordsPerPage, recordsPerPage);
    int noOfRecords = products.size();
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    if (page < 1) {
      page = 1;
    } else if (page > noOfRecords) {
      page = noOfRecords;
    }
    model.addAttribute("searchName", searchName);
    model.addAttribute("noOfPages", noOfPages);
    model.addAttribute("currentPage", page);
    model.addAttribute("products", products);
    return "product/product-list";
  }

  @GetMapping("/{id}")
  public String showUpdateForm(Model model, @PathVariable("id") String id) {
    Optional<Product> product = productService.findById(id);
    ProductDTO productDTO = new ProductDTO();
    if (product.isPresent()) {
      productDTO = modelMapper.map(product.get(), ProductDTO.class);
    }
    model.addAttribute("product", productDTO);
    return "product/edit-product";
  }

  @PostMapping("/{id}")
  public String update(@ModelAttribute("product") ProductDTO productDTO,
      BindingResult bindingResult, Model model, @PathVariable("id") String id,
      RedirectAttributes redirectAttributes) {
    productDTO.validate(productDTO, bindingResult);
    if (bindingResult.hasErrors()) {
      List<FieldError> errors = bindingResult.getFieldErrors();
      Map<String, String> errorList = new LinkedHashMap<>();
      for (FieldError item : errors) {
        String field = item.getField();
        System.out.println(field.toString());
        String msg = item.getDefaultMessage();
        System.out.println(msg.toString());
        errorList.put(field, msg);
      }
      model.addAttribute("errorList", errorList);
      model.addAttribute("product", productDTO);
      return "product/edit-product";
    }
    System.out.println(productDTO.toString());
    Product product = modelMapper.map(productDTO, Product.class);
    product.setProductId(id);
    productService.createOrUpdate(product);
    redirectAttributes.addFlashAttribute("message",
        "Update service successfully!");
    return "redirect:/product/list";
  }

  @PostMapping("/delete")
  public String deleteProduct(@RequestParam String productId, Model model,
      RedirectAttributes redirectAttributes) {
    System.out.println("ID COMPUTER " + productId);
    productService.delete(productId);
    redirectAttributes.addFlashAttribute("message",
        "Delete service successfully!");
    return "redirect:/product/list";
  }

}
