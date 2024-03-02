package com.fsoft.internet.repositories.payment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.internet.entities.Payment;
import com.fsoft.internet.entities.PaymentId;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, PaymentId> {

  @Query(value = "select * from payment where customer_id=:id", nativeQuery = true)
  List<Payment> getAllByCustomerId(@Param("id") String id);

  @Query(value = "select * from payment order by usingDate offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Payment> getRecordsForCurrentPage(@Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Query(value = "select * from payment where customer_id like :customerId and product_id like :productId order by usingDate offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Payment> search(@Param("customerId") String customerId,
      @Param("productId") String productId, @Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Query(value = "select * from payment where customer_id like :customerId and product_id like :productId ;", nativeQuery = true)
  List<Payment> search(@Param("customerId") String customerId,
      @Param("productId") String productId);

}
