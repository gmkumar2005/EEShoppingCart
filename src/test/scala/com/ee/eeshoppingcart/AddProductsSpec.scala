package com.ee.eeshoppingcart

import java.util.UUID

import org.scalatest.Inspectors._
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}


class AddProductsSpec extends FeatureSpec with GivenWhenThen with Matchers {

  info("As a user I should be able to add products to the shopping cart")
  info("then calculate the total price and tax amounts for the items contained in the cart")

  feature("Shopping Cart") {
    scenario("Step 1 : Add products to the shopping cart") {

      Given("An empty shopping cart")
      val sc = new ShoppingCart()
      val doveSoapId = UUID.randomUUID()
      val itemPrice = new java.math.BigDecimal(39.99).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP)
      val doveSoap = new Product("Dove Soap", itemPrice, doveSoapId)

      When("the user adds 5 Dove Soaps to the shopping cart")
      sc.addItem(doveSoap.getProductName, doveSoap.getPrice, doveSoap.getProductId, 5)

      Then("the shopping cart should contain 5 Dove Soaps each with a unit price of 39.99")
      sc.getItemCount should be(5)
      forAll(sc.getItems) { x => {
        val item = x.asInstanceOf[ShoppingItem]
        item.getItemPrice should be(doveSoap.getPrice)
      }
      }
      And("the shopping cart's total price should equal 199.95")
      val expectedTotalPrice = new java.math.BigDecimal(199.95).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue
      sc.getTotalPrice.doubleValue shouldEqual expectedTotalPrice
    }

  }

}
