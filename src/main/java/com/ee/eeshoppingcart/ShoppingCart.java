package com.ee.eeshoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingCart {

  public static int PRICE_SCALE = 2;

  public ShoppingCart() {

  }

  private List<ShoppingItem> items = new ArrayList<>();
  private BigDecimal totalPrice = new BigDecimal(0).setScale(PRICE_SCALE, BigDecimal.ROUND_HALF_UP);
  private int itemCount = 0;

  public void addItem(String productName, BigDecimal itemPrice, UUID productId, int itemQuantity) {
    ShoppingItem item = new ShoppingItem(productName, itemPrice, productId, itemQuantity);
    items.add(item);
    BigDecimal itemCost = (itemPrice
        .multiply(new BigDecimal(itemQuantity).setScale(PRICE_SCALE, BigDecimal.ROUND_HALF_UP)));
    totalPrice = totalPrice.add(itemCost);
    itemCount = itemCount + itemQuantity;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public List getItems() {
    return items;
  }

  public int getItemCount() {
    return itemCount;
  }
}
