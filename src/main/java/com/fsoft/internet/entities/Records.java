package com.fsoft.internet.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "record")
public class Records {

  @EmbeddedId
  private RecordId recordId;

  @JoinColumn(name = "computer_id")
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("computerId")
  private Computer computer;

  @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("customerId")
  private Customer customer;

  private Integer usingTime;

  public Records() {
  }

  public Records(RecordId recordId, Computer computer, Customer customer,
      Integer usingTime) {
    this.recordId = recordId;
    this.computer = computer;
    this.customer = customer;
    this.usingTime = usingTime;
  }

  public RecordId getRecordId() {
    return recordId;
  }

  public void setRecordId(RecordId recordId) {
    this.recordId = recordId;
  }

  public Computer getComputer() {
    return computer;
  }

  public void setComputer(Computer computer) {
    this.computer = computer;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Integer getUsingTime() {
    return usingTime;
  }

  public void setUsingTime(Integer usingTime) {
    this.usingTime = usingTime;
  }

  @Override
  public String toString() {
    return "Record [recordId=" + recordId + ", computer=" + computer
        + ", customer=" + customer + ", usingTime=" + usingTime + "]";
  }

}
