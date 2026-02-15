Feature: Shop - Buy products
  As a customer
  I want to buy products

  Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists

  Scenario: (1) - Buy one product
    When I buy "Bread" with quantity 2
    Then total should be 41.00
    And stock of product "Bread" should be 3

  Scenario: (2) - Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    Then total should be 121.00
    And stock of product "Bread" should be 3
    And stock of product "Jam" should be 9

  Scenario Outline: (3) - Cut stock of <product> directly
    When cut stock of product "<product>" with quantity <quantity>
    Then stock of product "<product>" should be <remaining>
    Examples:
      | product | quantity | remaining |
      | Bread   | 1        | 4         |
      | Jam     | 2        | 8         |
