Feature: Shop - Stock management
  As a stock employee

  Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists

  Scenario Outline: (1) - Cut stock of <product> directly
    When cut stock of product "<product>" with quantity <quantity>
    Then stock of product "<product>" should be <remaining>
    Examples:
      | product | quantity | remaining |
      | Bread   | 1        | 4         |
      | Jam     | 2        | 8         |
