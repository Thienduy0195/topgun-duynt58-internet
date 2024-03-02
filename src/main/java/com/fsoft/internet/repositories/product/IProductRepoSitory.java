package com.fsoft.internet.repositories.product;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.internet.entities.Product;

@Repository
@Transactional
public interface IProductRepoSitory extends JpaRepository<Product, Integer> {

  @Query(value = "select * from product where deleteStatus =0 order by productId offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Product> getRecordsForCurrentPage(@Param("start") int start,
      @Param("recordsPerPage") int recordsPerPage);

  @Query(value = "select * from product where productId =:id", nativeQuery = true)
  Optional<Product> findById(@Param("id") String id);

  @Query(value = "select * from product where product_name like :searchName and deleteStatus =0 order by productId offset :start ROWS FETCH FIRST :recordsPerPage ROWS ONLY;", nativeQuery = true)
  List<Product> searchByName(@Param("searchName") String searchName,
      @Param("start") int start, @Param("recordsPerPage") int recordsPerPage);

  @Modifying
  @Query(value = "update product set deleteStatus=1 where productId =:id", nativeQuery = true)
  void deleteById(@Param("id") String id);

}
