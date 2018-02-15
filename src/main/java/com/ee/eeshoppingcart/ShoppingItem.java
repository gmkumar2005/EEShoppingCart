package com.ee.eeshoppingcart;

import java.math.BigDecimal;
import java.util.UUID;

public class ShoppingItem {

  private String productName;
  private BigDecimal itemPrice;
  private UUID productId;


  private int itemQuantity;


  public ShoppingItem(String productName, BigDecimal itemPrice, UUID productId, int itemQuantity) {
    this.productName = productName;
    this.itemPrice = itemPrice;
    this.productId = productId;
    this.itemQuantity = itemQuantity;
  }


  public UUID getProductId() {
    return productId;
  }


  public String getProductName() {
    return productName;
  }


  public BigDecimal getItemPrice() {
    return itemPrice;
  }

  public int getItemQuantity() {
    return itemQuantity;
  }

}
