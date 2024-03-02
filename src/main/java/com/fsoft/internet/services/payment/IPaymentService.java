package com.fsoft.internet.services.payment;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fsoft.internet.entities.Payment;

public interface IPaymentService {

  void createOrUpdate(Payment payment);

  Page<Payment> findAll(Pageable pageable);

  Integer getLastId();

  int getNoOfRecords();

  List<Payment> getRecordsForCurrentPage(int i, int recordsPerPage);

  Optional<Payment> findById(String id);

  List<Payment> getList();

  List<Payment> getAllByCustomerId(String id);

  Double caculateTotal(List<Payment> payments);

  List<Payment> search(String customerId, String productId, int i,
      int recordsPerPage);

  List<Payment> search(String customerId, String productId);

}
