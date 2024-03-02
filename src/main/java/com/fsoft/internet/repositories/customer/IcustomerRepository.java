package com.fsoft.internet.repositories.customer;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.fsoft.internet.entities.Customer;

@Component
@Transactional
public interface IcustomerRepository extends JpaRepository<Customer, Integer> {

  @Query(value = "select * from customer where delete_status=0 order by customer_id offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Customer> getRecordsForCurrentPage(@Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Query(value = "select * from customer where delete_status=0;", nativeQuery = true)
  List<Customer> findAll();

  @Query(value = "select * from customer where customer_id =:id", nativeQuery = true)
  Optional<Customer> findById(@Param("id") String id);

  @Query(value = "select * from customer where phone_number =:phone", nativeQuery = true)
  Optional<Customer> findByPhoneNumber(@Param("phone") String phone);

  @Modifying
  @Query(value = "update customer set delete_status=1 where customer_id =:id", nativeQuery = true)
  void deleteById(@Param("id") String id);

  @Query(value = "select * from customer where customer_name like :searchName and address like :searchAddress and delete_status =0 order by customer_id offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Customer> searchByNameAndAddress(@Param("searchName") String searchName,
      @Param("searchAddress") String searchAddress, @Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Query(value = "select * from customer where email =:email", nativeQuery = true)
  Optional<Customer> findByEmail(@Param("email") String email);

  @Query(value = "select * from customer where customer_name like :searchName and address like :searchAddress and delete_status =0 ;", nativeQuery = true)
  List<Customer> searchByNameAndAddress(@Param("searchName") String searchName,
      @Param("searchAddress") String searchAddress);

}
