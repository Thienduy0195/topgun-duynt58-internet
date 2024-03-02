package com.fsoft.internet.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class PaymentId implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private String customerId;
  private String productId;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate usingDate;
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime usingHour;

  public PaymentId() {
  }

  public PaymentId(String customerId, String productId, LocalDate usingDate,
      LocalTime usingHour) {
    this.customerId = customerId;
    this.productId = productId;
    this.usingDate = usingDate;
    this.usingHour = usingHour;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public LocalDate getUsingDate() {
    return usingDate;
  }

  public void setUsingDate(LocalDate usingDate) {
    this.usingDate = usingDate;
  }

  public LocalTime getUsingHour() {
    return usingHour;
  }

  public void setUsingHour(LocalTime usingHour) {
    this.usingHour = usingHour;
  }

  @Override
  public String toString() {
    return "PaymentId [customerId=" + customerId + ", productId=" + productId
        + ", usingDate=" + usingDate + ", usingHour=" + usingHour + "]";
  }

}
