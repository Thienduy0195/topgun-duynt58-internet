package com.fsoft.internet.services.record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.internet.entities.Records;
import com.fsoft.internet.repositories.record.IRecordRepository;

@Service
public class RecordServiceImpl implements IRecordService {

  @Autowired
  private IRecordRepository recordRepository;

  @Override
  public void createOrUpdate(Records record) {
    recordRepository.save(record);
  }

  @Override
  public List<Records> getAllByCustomerId(String id) {
    return recordRepository.getAllByCustomerId(id);
  }

  @Override
  public int getNoOfRecords() {
    return recordRepository.findAll().size();
  }

  @Override
  public List<Records> getRecordsForCurrentPage(int start, int recordsPerPage) {
    return recordRepository.getRecordsForCurrentPage(start, recordsPerPage);
  }

  @Override
  public List<Records> search(String customerId, String computerId, int i,
      int recordsPerPage) {
    return recordRepository.search("%" + customerId + "%",
        "%" + computerId + "%", i, recordsPerPage);
  }

  @Override
  public List<Records> search(String customerId, String computerId) {
    // TODO Auto-generated method stub
    return recordRepository.search("%" + customerId + "%",
        "%" + computerId + "%");
  }

}
