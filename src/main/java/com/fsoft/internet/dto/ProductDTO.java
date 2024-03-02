package com.fsoft.internet.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fsoft.internet.utils.CheckIdExistsConstraint;

public class ProductDTO implements Validator {
  static final String REGEX_ID = "^SV-[0-9]{4}$";
  static final String REGEX_POSITIVE_NUMBER = "^-?[0-9]+(\\.[0-9]+)?$";

  @CheckIdExistsConstraint
  private String productId;

  @NotEmpty(message = "Required!")
  private String productName;

  @NotEmpty(message = "Required!")
  private String unit;

  @NotEmpty(message = "Required!")
  private String price;

  @NotEmpty(message = "Required!")
  private String image;

  private Integer deleteStatus = 0;

  public ProductDTO() {
  }

  public ProductDTO(String productId, String productName, String unit,
      String price, String image, Integer deleteStatus) {
    super();
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

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
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

  @Override
  public String toString() {
    return "ProductDTO [productId=" + productId + ", productName=" + productName
        + ", unit=" + unit + ", price=" + price + ", image=" + image + "]";
  }

  @Override
  public boolean supports(Class<?> clazz) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void validate(Object target, Errors errors) {
    ProductDTO productDTO = (ProductDTO) target;
    if (productDTO.getProductName().isEmpty()
        || "".equals(productDTO.getProductName())) {
      errors.rejectValue("productName", "productName", "Required!");
    }

    if (productDTO.getUnit().isEmpty() || "".equals(productDTO.getUnit())) {
      errors.rejectValue("unit", "unit", "Required!");
    }

    if (productDTO.getProductId().isEmpty()
        || "".equals(productDTO.getProductId())) {
      errors.rejectValue("productId", "productId", "Required!");
    } else if (!productDTO.getProductId().matches(REGEX_ID)) {
      errors.rejectValue("productId", "productId", "Wrong format SV-xxxx");
    }

    if (!productDTO.getPrice().matches(REGEX_POSITIVE_NUMBER)) {
      errors.rejectValue("price", "price", "Price must be positive number!");
    }

    if (productDTO.getImage().isEmpty() || "".equals(productDTO.getImage())) {
      errors.rejectValue("image", "image", "Required!");
    }

  }

}
