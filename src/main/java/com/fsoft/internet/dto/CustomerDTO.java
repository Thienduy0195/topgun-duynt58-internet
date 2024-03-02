package com.fsoft.internet.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fsoft.internet.utils.CheckEmailExitsConstraint;
import com.fsoft.internet.utils.CheckIdExistsConstraint;
import com.fsoft.internet.utils.CheckPhoneExitsConstraint;

public class CustomerDTO implements Validator {

  static final String REGEX_ID = "^CUS[0-9]{5}$";
  static final String REGEX_PHONE = "^(090|091|032|033|034|035|036|037|038|039|070|079|077|076|078|083|084|085|081|082|056|058|059|\\\\(84\\\\)\\\\+90|\\\\(84\\\\)\\\\+91)[0-9]{7}$";
  static final String REGEX_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

//  @NotEmpty(message = "Code is required!")
  @Pattern(regexp = REGEX_ID, message = "Wrong format CUSxxxxx")
  @CheckIdExistsConstraint
  private String customerId;

  @Pattern(regexp = "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêêềễìíứừựớờợòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s?]+$", message = "Name cannot be blank or contain special characters!")
  private String name;

  @NotEmpty(message = "Address is required!")
  private String address;

  @NotEmpty(message = "Phone is required!")
  @CheckPhoneExitsConstraint
  private String phoneNumber;

  @NotEmpty(message = "Email is required!")
  @CheckEmailExitsConstraint
  private String email;

  private Integer deleteStatus = 0;

  public CustomerDTO() {
  }

  public CustomerDTO(String customerId, String name, String address,
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

  @Override
  public String toString() {
    return "CustomerDTO [customerId=" + customerId + ", name=" + name
        + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email="
        + email + ", deleteStatus=" + deleteStatus + "]";
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return false;
  }

  @Override
  public void validate(Object target, Errors errors) {
    CustomerDTO customerDTO = (CustomerDTO) target;
    if (customerDTO.getCustomerId() == null
        || "".equals(customerDTO.getCustomerId())) {
      errors.rejectValue("customerId", "customerId", "Required!");
    } else if (!customerDTO.getCustomerId().matches(REGEX_ID)) {
      errors.rejectValue("customerId", "code.format", "Wrong format CUS-xxxx");
    }

    String phone = customerDTO.getPhoneNumber();
    // validate phone:
    if (phone.isEmpty()) {
      errors.rejectValue("phoneNumber", "phone.empty", "Required!");
    } else if (phone.length() > 12 || phone.length() < 10) {
      errors.rejectValue("phoneNumber", "phone.length",
          "Phone must be from 10 to 12 characters!");
    } else if (!phone.startsWith("0")) {
      errors.rejectValue("phoneNumber", "phone.startWith",
          "Phone must start with 0!");
    } else if (!phone.matches("(^$|[0-9]*$)")) {
      errors.rejectValue("phoneNumber", "phone.matchs",
          "Phone is in wrong format!");
    }
    if (customerDTO.getName() == null || customerDTO.getName().isEmpty()) {
      errors.rejectValue("name", "name.error", "Required!");
    }

    if (customerDTO.getEmail() == null || customerDTO.getEmail().isEmpty()) {
      errors.rejectValue("email", "email.error", "Required!");
    }

    if (customerDTO.getAddress() == null
        || customerDTO.getAddress().isEmpty()) {
      errors.rejectValue("address", "address.error", "Required!");
    }

  }

}
