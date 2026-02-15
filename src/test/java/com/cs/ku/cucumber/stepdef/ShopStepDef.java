package com.cs.ku.cucumber.stepdef;

import com.cs.ku.shop.Order;
import com.cs.ku.shop.Product;
import com.cs.ku.shop.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopStepDef {

    private ProductCatalog catalog;
    private Order order;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        Product product = catalog.getProduct(name);
        order.addItem(product, quantity);
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @When("cut stock of product {string} with quantity {int}")
    public void cut_stock_of_product_with_quantity(String name, int quantity) {
        var product = catalog.getProduct(name);
        product.cutStock(quantity);
    }

    @Then("stock of product {string} should be {int}")
    public void stock_of_product_should_be(String name, int stock) {
        var product = catalog.getProduct(name);
        assertEquals(stock, product.getStock());
    }
}

