package com.fsoft.internet.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class RecordDTO {

  @NotBlank(message = "Required!")
  private String customerId;

  @NotBlank(message = "Required!")
  private String computerId;

  @NotNull(message = "Required!")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate startingDate;

  @NotNull(message = "Required!")
//  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalTime startingHour;

  @NotNull(message = "Required!")
  private Integer usingTime;

  public RecordDTO() {
  }

  public RecordDTO(String customerId, String computerId, LocalDate startingDate,
      LocalTime startingHour, Integer usingTime) {
    super();
    this.customerId = customerId;
    this.computerId = computerId;
    this.startingDate = startingDate;
    this.startingHour = startingHour;
    this.usingTime = usingTime;
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

  public Integer getUsingTime() {
    return usingTime;
  }

  public void setUsingTime(Integer usingTime) {
    this.usingTime = usingTime;
  }

  @Override
  public String toString() {
    return "RecordDTO [customerId=" + customerId + ", computerId=" + computerId
        + ", startingDate=" + startingDate + ", startingHour=" + startingHour
        + ", usingTime=" + usingTime + "]";
  }

}
