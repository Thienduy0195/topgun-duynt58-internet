package com.fsoft.internet.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "computer")
public class Computer {

  @Id
  @Column(name = "computer_id")
  private String computerId;

  @Column(name = "location", columnDefinition = "nvarchar(255)")
  private String location;

  @Column(name = "status", columnDefinition = "nvarchar(255)")
  private String status;

  @Column(name = "delete_status")
  private Integer deleteStatus;

  @OneToMany(mappedBy = "computer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  Set<Records> records;

  public Computer() {
  }

  public Computer(String computerId, String location, String status,
      Integer deleteStatus) {
    this.computerId = computerId;
    this.location = location;
    this.status = status;
    this.deleteStatus = deleteStatus;
  }

  public String getComputerId() {
    return computerId;
  }

  public void setComputerId(String computerId) {
    this.computerId = computerId;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getDeleteStatus() {
    return deleteStatus;
  }

  public void setDeleteStatus(Integer deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  public Set<Records> getRecords() {
    return records;
  }

  public void setRecords(Set<Records> records) {
    this.records = records;
  }

  @Override
  public String toString() {
    return "Computer [computerId=" + computerId + ", location=" + location
        + ", status=" + status + ", deleteStatus=" + deleteStatus + "]";
  }

}
