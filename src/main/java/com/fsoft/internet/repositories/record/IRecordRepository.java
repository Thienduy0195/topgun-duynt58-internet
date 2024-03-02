package com.fsoft.internet.repositories.record;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.internet.entities.RecordId;
import com.fsoft.internet.entities.Records;

@Repository
public interface IRecordRepository extends JpaRepository<Records, RecordId> {

  @Query(value = "select * from record where customer_id=:id", nativeQuery = true)
  List<Records> getAllByCustomerId(@Param("id") String id);

  @Query(value = "select * from record order by startingDate offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Records> getRecordsForCurrentPage(@Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Query(value = "select * from record where customer_id like :customerId and computer_id like :computerId order by startingDate offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Records> search(@Param("customerId") String customerId,
      @Param("computerId") String computerId, @Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Query(value = "select * from record where customer_id like :customerId and computer_id like :computerId ;", nativeQuery = true)
  List<Records> search(@Param("customerId") String customerId,
      @Param("computerId") String computerId);

}
