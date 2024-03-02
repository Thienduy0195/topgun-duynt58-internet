package com.fsoft.internet.services.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsoft.internet.entities.Customer;
import com.fsoft.internet.repositories.customer.IcustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

  @Autowired
  private IcustomerRepository customerRepository;

  @Autowired
  public CustomerServiceImpl(IcustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public IcustomerRepository getCustomerRepository() {
    return customerRepository;
  }

  @Autowired
  public void setCustomerRepository(IcustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public CustomerServiceImpl() {
  }

  @Override
  public void createOrUpdate(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public Page<Customer> findAll(Pageable pageable) {
    return customerRepository.findAll(pageable);
  }

  @Override
  public Integer getLastId() {
    Optional<Customer> customer = customerRepository.findAll().stream()
        .reduce((first, second) -> second);
    if (customer.isPresent()) {
      return Integer.parseInt(customer.get().getCustomerId().substring(3));
    }
    return 0;
  }

  @Override
  public int getNoOfRecords() {
    return customerRepository.findAll().size();
  }

  @Override
  public List<Customer> getRecordsForCurrentPage(int start,
      int recordsPerPage) {
    return customerRepository.getRecordsForCurrentPage(start, recordsPerPage);
  }

  @Override
  public Optional<Customer> findById(String id) {
    return customerRepository.findById(id.toUpperCase());
  }

  @Override
  public List<Customer> getList() {
    return customerRepository.findAll();
  }

  @Override
  public boolean delete(String id) {
    try {
      customerRepository.deleteById(id);
    } catch (Exception e) {
      System.out.println("error add repo");
    }
    return customerRepository.findById(id).get().getDeleteStatus() == 1;
  }

  @Override
  public List<Customer> searchByNameAndAddress(String searchName,
      String searchAddress, int start, int recordsPerPage) {
    return customerRepository.searchByNameAndAddress("%" + searchName + "%",
        "%" + searchAddress + "%", start, recordsPerPage);
  }

  @Override
  public Optional<Customer> findByPhoneNumber(String phone) {
    return customerRepository.findByPhoneNumber(phone);
  }

  @Override
  public Optional<Customer> findByEmail(String email) {
    return customerRepository.findByEmail(email);
  }

  @Override
  public List<Customer> searchByNameAndAddress(String searchName,
      String searchAddress) {
    return customerRepository.searchByNameAndAddress("%" + searchName + "%",
        "%" + searchAddress + "%");
  }

}
