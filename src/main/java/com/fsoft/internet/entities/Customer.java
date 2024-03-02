package com.fsoft.internet.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "customer")
public class Customer {

  @Id
  @Column(name = "customer_id")
  private String customerId;

  @Column(name = "customer_name", columnDefinition = "nvarchar(255)")
  private String name;

  @Column(name = "address", columnDefinition = "nvarchar(255)")
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

  private String email;

  @Column(name = "delete_status")
  private Integer deleteStatus;

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  Set<Payment> payments;

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  Set<Records> records;

  public Customer() {
  }

  public Customer(String customerId, String name, String address,
      String phoneNumber, String email, Integer deleteStatus) {
    this.customerId = customerId;
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.deleteStatus = deleteStatus;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public Set<Records> getRecords() {
    return records;
  }

  public void setRecords(Set<Records> records) {
    this.records = records;
  }

  @Override
  public String toString() {
    return "Customer [customerId=" + customerId + ", name=" + name
        + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email="
        + email + ", deleteStatus=" + deleteStatus + "]";
  }

}
