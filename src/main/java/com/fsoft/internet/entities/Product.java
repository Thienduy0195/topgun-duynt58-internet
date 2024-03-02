package com.fsoft.internet.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "product")
public class Product {

  @Id
  private String productId;

  @Column(name = "product_name", columnDefinition = "nvarchar(255)")
  private String productName;

  @Column(name = "unit", columnDefinition = "nvarchar(255)")
  private String unit;

  private Double price;

  private String image;

  private Integer deleteStatus;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  Set<Payment> payments;

  public Product() {
  }

  public Product(String productId, String productName, String unit,
      Double price, String image, Integer deleteStatus) {
    this.productId = productId;
    this.productName = productName;
    this.unit = unit;
    this.price = price;
    this.image = image;
    this.deleteStatus = deleteStatus;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Integer getDeleteStatus() {
    return deleteStatus;
  }

  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  public Set<Payment> getPayments() {
    return payments;
  }

  public void setPayments(Set<Payment> payments) {
    this.payments = payments;
  }

  @Override
  public String toString() {
    return "Product [productId=" + productId + ", productName=" + productName
        + ", unit=" + unit + ", price=" + price + "]";
  }

}
