package com.fsoft.internet.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fsoft.internet.utils.CheckIdExistsConstraint;

public class ComputerDTO implements Validator {

  static final String REGEX_ID = "^COM-[0-9]{4}$";

  @Pattern(regexp = REGEX_ID, message = "Wrong format COM-xxxx")
  @CheckIdExistsConstraint
  private String computerId;

  @NotBlank(message = "Location is required!")
  private String location;

  private String status = "Offline";

  private Integer deleteStatus = 0;

  public ComputerDTO() {
  }

  public ComputerDTO(String computerId, String location, String status,
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

  @Override
  public String toString() {
    return "ComputerDTO [computerId=" + computerId + ", location=" + location
        + ", status=" + status + ", deleteStatus=" + deleteStatus + "]";
  }

  @Override
  public boolean supports(Class<?> clazz) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void validate(Object target, Errors errors) {
    ComputerDTO computerDTO = (ComputerDTO) target;
    if (computerDTO.getComputerId() == null
        || "".equals(computerDTO.getComputerId())) {
      errors.rejectValue("computerId", "computerId", "Required!");
    } else if (!computerDTO.getComputerId().matches(REGEX_ID)) {
      errors.rejectValue("computerId", "code.format", "Wrong format COM-xxxx");
    }

    if (computerDTO.getLocation() == null
        || "".equals(computerDTO.getLocation())) {
      errors.rejectValue("location", "location", "Required!");
    }

  }

}
