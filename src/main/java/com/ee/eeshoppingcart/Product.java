package com.ee.eeshoppingcart;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {

  private String productName;
  private BigDecimal price;
  private UUID productId;

  public Product(String productName, BigDecimal price, UUID productId) {
    this.productName = productName;
    this.price = price;
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public UUID getProductId() {
    return productId;
  }
}
