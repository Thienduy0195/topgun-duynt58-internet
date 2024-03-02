package com.fsoft.internet.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "payment")
public class Payment {

  @EmbeddedId
  private PaymentId paymentId;

  @JoinColumn(name = "product_id")
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("productId")
  private Product product;

  @JoinColumn(name = "customer_id")
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("customerId")
  private Customer customer;

  private Integer amount;

  public Payment() {
  }

  public Payment(PaymentId paymentId, Product product, Customer customer,
      Integer amount) {
    this.paymentId = paymentId;
    this.product = product;
    this.customer = customer;
    this.amount = amount;
  }

  public PaymentId getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(PaymentId paymentId) {
    this.paymentId = paymentId;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "Payment [paymentId=" + paymentId + ", product=" + product
        + ", customer=" + customer + ", amount=" + amount + "]";
  }

}
