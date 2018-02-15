package com.ee.eeshoppingcart;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ShoppingCart {

  public static int PRICE_SCALE = 2;

  private Map<UUID, ShoppingItem> items = new HashMap<>();
  private BigDecimal taxRate;

  public void addItem(String productName, BigDecimal itemPrice, UUID productId, int itemQuantity) {
    ShoppingItem existingItem = items.get(productId);
    if (existingItem != null) {
      ShoppingItem item = new ShoppingItem(existingItem.getProductName(),
          existingItem.getItemPrice(), productId, existingItem.getItemQuantity() + itemQuantity);
      items.put(productId, item);
    } else {
      ShoppingItem item = new ShoppingItem(productName, itemPrice, productId, itemQuantity);
      items.put(productId, item);
    }

  }

  private BigDecimal calculateItemPrice() {
    BigDecimal totalPrice = new BigDecimal(0);
    for (Map.Entry<UUID, ShoppingItem> entry : items.entrySet()) {
      totalPrice = totalPrice.add(entry.getValue().getItemPrice()
          .multiply(new BigDecimal(entry.getValue().getItemQuantity())));
    }

    return totalPrice.setScale(PRICE_SCALE, BigDecimal.ROUND_HALF_UP);
  }

  public BigDecimal getTotalPrice() {
    return calculateItemPrice().add(getTaxAmount());
  }

  public Map<UUID, ShoppingItem> getItems() {
    return items;
  }

  public int getItemCount() {
    int itemCount = 0;
    for (Map.Entry<UUID, ShoppingItem> entry : items.entrySet()) {
      itemCount = itemCount + entry.getValue().getItemQuantity();
    }

    return itemCount;
  }

  public BigDecimal getTaxAmount() {
    if (taxRate == null) {
      return BigDecimal.ZERO;
    } else {
      return calculateItemPrice().multiply(taxRate)
          .divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP)
          .setScale(PRICE_SCALE, BigDecimal.ROUND_HALF_UP);
    }
  }

  public BigDecimal getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(BigDecimal taxRate) {
    this.taxRate = taxRate;
  }
}
