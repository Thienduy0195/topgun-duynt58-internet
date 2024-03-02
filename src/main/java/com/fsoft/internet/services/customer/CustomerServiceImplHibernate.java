package com.fsoft.internet.services.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fsoft.internet.entities.Customer;

public class CustomerServiceImplHibernate implements ICustomerService {

  @Override
  public void createOrUpdate(Customer customer) {
    // TODO Auto-generated method stub

  }

  @Override
  public Page<Customer> findAll(Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Integer getLastId() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getNoOfRecords() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Optional<Customer> findById(String id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public Optional<Customer> findByPhoneNumber(String phone) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public List<Customer> getList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean delete(String id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public List<Customer> searchByNameAndAddress(String searchName,
      String searchAddress, int i, int recordsPerPage) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Customer> getRecordsForCurrentPage(int start,
      int recordsPerPage) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Customer> findByEmail(String email) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public List<Customer> searchByNameAndAddress(String searchName,
      String searchAddress) {
    // TODO Auto-generated method stub
    return null;
  }

}
