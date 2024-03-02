package com.fsoft.internet.repositories.computer;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.internet.entities.Computer;

@Repository
@Transactional
public interface IComputerRepository extends JpaRepository<Computer, Integer> {

  @Query(value = "select * from computer where delete_status=0 order by computer_id offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Computer> getRecordsForCurrentPage(@Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Query(value = "select * from computer where delete_status=0;", nativeQuery = true)
  List<Computer> findAll();

  @Query(value = "select * from computer where computer_id =:id and delete_status=0", nativeQuery = true)
  Optional<Computer> findById(@Param("id") String id);

  @Query(value = "select * from computer where computer_id like :searchId and location like :searchLocation and status like :status and delete_status=0 order by computer_id offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Computer> search(@Param("searchId") String searchId,
      @Param("searchLocation") String searchLocation,
      @Param("status") String status, @Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Modifying
  @Query(value = "update computer set delete_status=1 where computer_id =:id", nativeQuery = true)
  void deleteById(@Param("id") String id);

  @Query(value = "select * from computer where computer_id like :searchId and location like :searchLocation and status like :status and delete_status=0 ;", nativeQuery = true)
  List<Computer> search(@Param("searchId") String searchId,
      @Param("searchLocation") String searchLocation,
      @Param("status") String status);

  @Query(value = "select * from computer where delete_status=0 and status = 'Offline' ;", nativeQuery = true)
  List<Computer> getOfflineList();

}
