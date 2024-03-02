package com.fsoft.internet.services.computer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsoft.internet.entities.Computer;
import com.fsoft.internet.repositories.computer.IComputerRepository;

@Service
public class ComputerServiceImpl implements IComputerService {
  @Autowired
  private IComputerRepository computerRepository;

  public ComputerServiceImpl() {

  }

  @Override
  public void createOrUpdate(Computer computer) {
    computerRepository.save(computer);
  }

  @Override
  public Page<Computer> findAll(Pageable pageable) {
    List<Computer> computers = computerRepository.findAll(pageable)
        .getContent();
    System.out.println("COMPUTER LIST");
    for (Computer computer : computers) {
      System.out.println(computer.toString());
    }
    return computerRepository.findAll(pageable);
  }

  @Override
  public List<Computer> getRecordsForCurrentPage(int start,
      int recordsPerPage) {
    return computerRepository.getRecordsForCurrentPage(start, recordsPerPage);
  }

  @Override
  public int getNoOfRecords() {
    return computerRepository.findAll().size();
  }

  @Override
  public Optional<Computer> findById(String id) {
    return computerRepository.findById(id.toUpperCase());
  }

  @Override
  public List<Computer> getList() {
    return computerRepository.findAll();
  }

  @Override
  public List<Computer> search(String searchId, String searchLocation,
      String status, int start, int recordsPerPage) {
    return computerRepository.search("%" + searchId + "%",
        "%" + searchLocation + "%", "%" + status + "%", start, recordsPerPage);
  }

  @Override
  public boolean delete(String id) {
    computerRepository.deleteById(id);
    return false;
  }

  @Override
  public List<Computer> search(String searchId, String searchLocation,
      String status) {
    return computerRepository.search("%" + searchId + "%",
        "%" + searchLocation + "%", "%" + status + "%");
  }

  @Override
  public List<Computer> getOfflineList() {
    return computerRepository.getOfflineList();
  }

}
