package com.fsoft.internet.services.computer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fsoft.internet.entities.Computer;

public interface IComputerService {

  void createOrUpdate(Computer computer);

  Page<Computer> findAll(Pageable pageable);

  List<Computer> getRecordsForCurrentPage(int start, int recordsPerPage);

  int getNoOfRecords();

  Optional<Computer> findById(String id);

  List<Computer> getList();

  List<Computer> search(String searchId, String searchLocation, String status,
      int i, int recordsPerPage);

  boolean delete(String id);

  List<Computer> search(String searchId, String searchLocation, String status);

  List<Computer> getOfflineList();

}
