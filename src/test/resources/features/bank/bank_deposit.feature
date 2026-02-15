Feature: Bank - Deposit
  As a customer
  I want to deposit from my account using ATM

  Background:
    Given a customer with id 1 and pin 111 with balance 3000 exists
    When I login to ATM with id 1 and pin 111

  Scenario Outline: Deposit amount to balance
    When I deposit <amount> to ATM
    Then my account balance is <balance>
    Examples:
      | amount | balance |
      | 300.00 | 3300.00 |
      | 150.50 | 3150.50 |
      | 0.00   | 3000.00 |

  Scenario: Deposit negative amount to balance
    When I deposit negative amount -100.00 to ATM
    Then my account balance is 3000.00