package com.ee.eeshoppingcart

import java.util.UUID

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}


class AddProductsSpec extends FeatureSpec with GivenWhenThen with Matchers {

  info("As a user I should be able to add products to the shopping cart")
  info("then calculate the total price and tax amounts for the items contained in the cart")

  feature("Shopping Cart") {
    scenario("Step 1 : Add products to the shopping cart.") {

      Given("An empty shopping cart")
      val sc = new ShoppingCart()
      val doveSoapId = UUID.randomUUID()
      val itemPrice = new java.math.BigDecimal(39.99).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP)
      val doveSoap = new Product("Dove Soap", itemPrice, doveSoapId)

      When("the user adds 5 Dove Soaps to the shopping cart")
      sc.addItem(doveSoap.getProductName, doveSoap.getPrice, doveSoap.getProductId, 5)

      Then("the shopping cart should contain 5 Dove Soaps each with a unit price of 39.99")
      sc.getItemCount should be(5)
      val expectedPrice = new java.math.BigDecimal(39.99).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue
      val actualPrice = sc.getItems.get(doveSoapId).getItemPrice.doubleValue
      actualPrice shouldEqual expectedPrice

      And("the shopping cart s total price should equal 199.95")
      val expectedTotalPrice = new java.math.BigDecimal(199.95).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue
      sc.getTotalPrice.doubleValue shouldEqual expectedTotalPrice
    }

  }
  scenario("Step 2: Add additional products of the same type to the shopping cart.") {
    Given("An empty shopping cart")
    val sc = new ShoppingCart()
    And("a product, Dove Soap with a unit price of 39.99")
    val doveSoapId = UUID.randomUUID()
    val itemPrice = new java.math.BigDecimal(39.99).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP)
    val doveSoap = new Product("Dove Soap", itemPrice, doveSoapId)

    When("the user adds 5 Dove Soaps to the shopping cart")
    sc.addItem(doveSoap.getProductName, doveSoap.getPrice, doveSoap.getProductId, 5)
    And("then adds another 3 Dove Soaps to the shopping cart")
    sc.addItem(doveSoap.getProductName, doveSoap.getPrice, doveSoap.getProductId, 3)

    Then("the shopping cart should contain 8 Dove Soaps each with a unit price of 39.99")
    sc.getItemCount should be(8)
    And("the shopping cart s total price should equal 319.92")
    val expectedTotalPrice = new java.math.BigDecimal(319.92).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue
    sc.getTotalPrice.doubleValue shouldEqual expectedTotalPrice
    val shoppingItems = sc.getItems
    val shoppingItems2 = sc.getItems
  }

  scenario("Step 3: Calculate the tax rate of the shopping cart with multiple items") {
    Given("An empty shopping cart")
    val sc = new ShoppingCart()
    And("a product, Dove Soap with a unit price of 39.99")
    val doveSoapId = UUID.randomUUID()
    val doveitemPrice = new java.math.BigDecimal(39.99).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP)
    val doveSoap = new Product("Dove Soap", doveitemPrice, doveSoapId)
    And("another product, Axe Deo with a unit price of 99.99")
    val axeSoapId = UUID.randomUUID()
    val axeitemPrice = new java.math.BigDecimal(99.99).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP)
    val axeSoap = new Product("Axe Deo", axeitemPrice, axeSoapId)
    And("a tax rate of 12.5%")
    val taxRate = new java.math.BigDecimal(12.5).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP)
    sc.setTaxRate(taxRate)

    When("the user adds 2 Dove Soaps to the shopping cart")
    sc.addItem(doveSoap.getProductName, doveSoap.getPrice, doveSoap.getProductId, 2)
    And("then adds 2 Axe Deo s to the shopping cart")
    sc.addItem(axeSoap.getProductName, axeSoap.getPrice, axeSoap.getProductId, 2)
    Then("The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99")
    sc.getItems.get(doveSoapId).getItemQuantity should be(2)
    val expectedUnitPrice = doveitemPrice.doubleValue
    sc.getItems.get(doveSoapId).getItemPrice.doubleValue shouldEqual expectedUnitPrice
    And("the shopping cart should contain 2 Axe Deo’s each with a unit price of 99.99")
    sc.getItems.get(axeSoapId).getItemQuantity should be(2)
    val expectedAxeUnitPrice = axeitemPrice.doubleValue
    sc.getItems.get(axeSoapId).getItemPrice.doubleValue shouldEqual expectedAxeUnitPrice
    And("the total tax amount should equal 35.00")
    val expectedTaxAmount = new java.math.BigDecimal(35.00).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue
    sc.getTaxAmount.doubleValue shouldEqual expectedTaxAmount
    And("the shopping cart’s total price should equal 314.96")
    val expectedTotalPrice = new java.math.BigDecimal(314.96).setScale(ShoppingCart.PRICE_SCALE, java.math.BigDecimal.ROUND_HALF_UP).doubleValue
    sc.getTotalPrice.doubleValue shouldEqual expectedTotalPrice

  }

}
