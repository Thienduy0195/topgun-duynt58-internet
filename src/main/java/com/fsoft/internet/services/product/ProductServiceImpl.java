package com.fsoft.internet.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsoft.internet.entities.Product;
import com.fsoft.internet.repositories.product.IProductRepoSitory;

@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private IProductRepoSitory productRepoSitory;

  @Override
  public void createOrUpdate(Product product) {
    productRepoSitory.save(product);

  }

  @Override
  public Page<Product> findAll(Pageable pageable) {
    return productRepoSitory.findAll(pageable);
  }

  @Override
  public Integer getLastId() {
    Optional<Product> product = productRepoSitory.findAll().stream()
        .reduce((first, second) -> second);
    if (product.isPresent()) {
      Integer lastId = Integer
          .parseInt((product.get().getProductId().substring(3)));
      return lastId;
    } else {
      return 0;
    }
  }

  @Override
  public int getNoOfRecords() {
    return productRepoSitory.findAll().size();
  }

  @Override
  public List<Product> getRecordsForCurrentPage(int start, int recordsPerPage) {
    // TODO Auto-generated method stub
    return productRepoSitory.getRecordsForCurrentPage(start, recordsPerPage);
  }

  @Override
  public Optional<Product> findById(String id) {
    return productRepoSitory.findById(id.toUpperCase());
  }

  @Override
  public List<Product> getList() {
    return productRepoSitory.findAll();
  }

  @Override
  public List<Product> searchByName(String searchName, int start,
      int recordsPerPage) {
    return productRepoSitory.searchByName("%" + searchName + "%", start,
        recordsPerPage);
  }

  @Override
  public boolean delete(String productId) {
    productRepoSitory.deleteById(productId);
    return false;
  }

}
