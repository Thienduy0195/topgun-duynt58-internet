package com.fsoft.internet.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class PaymentDTO {

  @NotBlank(message = "Required!")
  private String customerId;

  @NotBlank(message = "Required!")
  private String productId;

  @NotNull(message = "Required!")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate usingDate;

  @NotNull(message = "Required!")
  private LocalTime usingHour;

  @NotNull(message = "Required!")
  private Integer amount;

  public PaymentDTO() {
  }

  public PaymentDTO(String customerId, String productId, LocalDate usingDate,
      LocalTime usingHour, Integer amount) {
    super();
    this.customerId = customerId;
    this.productId = productId;
    this.usingDate = usingDate;
    this.usingHour = usingHour;
    this.amount = amount;
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

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "PaymentDTO [customerId=" + customerId + ", productId=" + productId
        + ", usingDate=" + usingDate + ", usingHour=" + usingHour + ", amount="
        + amount + "]";
  }

}
