package com.cs.ku.lecture.cucumber.stepdef;

import com.cs.ku.lecture.bank.ATM;
import com.cs.ku.lecture.bank.Bank;
import com.cs.ku.lecture.bank.Customer;
import com.cs.ku.lecture.bank.exception.NotEnoughBalanceException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ATMStepDef {

    ATM atm;
    Bank bank;
    boolean validLogin;

    @Before
    public void init() {
        bank = new Bank("KU Bank");
        atm = new ATM(bank);
    }

    @Given("a customer with id {int} and pin {int} with balance {float} exists")
    public void givenCustomerData(int id, int pin, double balance) {
        bank.openAccount(new Customer(id, pin, balance));
    }

    @When("I login to ATM with id {int} and pin {int}")
    public void whenLoginToATM(int id, int pin) {
        validLogin = atm.validateCustomer(id, pin);
    }

    @When("I withdraw {float} from ATM")
    public void whenWithdrawFromATM(double amount) throws NotEnoughBalanceException {
        atm.withdraw(amount);
    }

    @When("I overdraw {float} from ATM")
    public void whenOverdrawFromATM(double amount) throws NotEnoughBalanceException {
        assertThrows(NotEnoughBalanceException.class,
                () -> atm.withdraw(amount));
    }

    @When("I deposit {float} to ATM")
    public void whenDepositToATM(double amount) {
        atm.deposit(amount);
    }

    @When("I deposit negative amount {float} to ATM")
    public void whenDepositNegativeAmountToATM(double amount) {
        assertThrows(IllegalArgumentException.class,
                () -> atm.deposit(amount));
    }

    @When("I transfer {float} to customer id {int}")
    public void whenTransferAmountToCustomer(double amount, int toId) throws NotEnoughBalanceException {
        atm.transfer(toId, amount);
    }

    @Then("customer id {int} account balance is {float}")
    public void verifyCustomerBalance(int id, double balance) {
        assertEquals(balance, bank.getCustomer(id).getAccount().getBalance());
    }

    @Then("my account balance is {float}")
    public void verifyAccountBalance(double balance) {
        assertEquals(balance, atm.getBalance());
    }

}
