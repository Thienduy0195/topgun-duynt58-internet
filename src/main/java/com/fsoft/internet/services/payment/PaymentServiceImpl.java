package com.fsoft.internet.services.payment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsoft.internet.entities.Payment;
import com.fsoft.internet.repositories.payment.IPaymentRepository;

@Service
public class PaymentServiceImpl implements IPaymentService {

  @Autowired
  private IPaymentRepository paymentRepository;

  public PaymentServiceImpl() {
  }

  @Override
  public void createOrUpdate(Payment payment) {
    paymentRepository.save(payment);

  }

  @Override
  public Page<Payment> findAll(Pageable pageable) {
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
    return paymentRepository.findAll().size();
  }

  @Override
  public List<Payment> getRecordsForCurrentPage(int start, int recordsPerPage) {
    return paymentRepository.getRecordsForCurrentPage(start, recordsPerPage);
  }

  @Override
  public Optional<Payment> findById(String id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public List<Payment> getList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Payment> getAllByCustomerId(String id) {
    return paymentRepository.getAllByCustomerId(id);
  }

  @Override
  public Double caculateTotal(List<Payment> payments) {
    if (payments.isEmpty()) {
      return 0.0;
    } else {
      Double totalDouble = 0.0;
      for (Payment payment : payments) {
        totalDouble += payment.getProduct().getPrice() * payment.getAmount();
      }
      return totalDouble;
    }

  }

  @Override
  public List<Payment> search(String customerId, String productId, int i,
      int recordsPerPage) {
    return paymentRepository.search("%" + customerId + "%",
        "%" + productId + "%", i, recordsPerPage);
  }

  @Override
  public List<Payment> search(String customerId, String productId) {
    return paymentRepository.search("%" + customerId + "%",
        "%" + productId + "%");
  }

}
