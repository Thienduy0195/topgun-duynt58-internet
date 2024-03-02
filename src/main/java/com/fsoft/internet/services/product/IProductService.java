package com.fsoft.internet.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fsoft.internet.entities.Product;

public interface IProductService {

  void createOrUpdate(Product product);

  Page<Product> findAll(Pageable pageable);

  Integer getLastId();

  int getNoOfRecords();

  List<Product> getRecordsForCurrentPage(int i, int recordsPerPage);

  Optional<Product> findById(String id);

  List<Product> getList();

  List<Product> searchByName(String searchName, int i, int recordsPerPage);

  boolean delete(String productId);

}
