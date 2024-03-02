package com.fsoft.internet.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class RecordId implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @NotBlank(message = "Required!")
  private String customerId;

  @NotBlank(message = "Required!")
  private String computerId;

  @NotNull(message = "Required!")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startingDate;

  @NotNull(message = "Required!")
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime startingHour;

  public RecordId() {
  }

  public RecordId(String customerId, String computerId, LocalDate startingDate,
      LocalTime startingHour) {
    super();
    this.customerId = customerId;
    this.computerId = computerId;
    this.startingDate = startingDate;
    this.startingHour = startingHour;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getComputerId() {
    return computerId;
  }

  public void setComputerId(String computerId) {
    this.computerId = computerId;
  }

  public LocalDate getStartingDate() {
    return startingDate;
  }

  public void setStartingDate(LocalDate startingDate) {
    this.startingDate = startingDate;
  }

  public LocalTime getStartingHour() {
    return startingHour;
  }

  public void setStartingHour(LocalTime startingHour) {
    this.startingHour = startingHour;
  }

  @Override
  public String toString() {
    return "RecordId [customerId=" + customerId + ", computerId=" + computerId
        + ", startingDate=" + startingDate + ", startingHour=" + startingHour
        + "]";
  }

}
