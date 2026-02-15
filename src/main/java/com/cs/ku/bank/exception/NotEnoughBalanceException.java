package com.cs.ku.bank.exception;

public class NotEnoughBalanceException extends RuntimeException {

    public NotEnoughBalanceException() {
        super("Not enough balance to perform the operation.");
    }

    public NotEnoughBalanceException(Throwable cause) {
        super("Not enough balance to perform the operation.", cause);
    }

    public NotEnoughBalanceException(String message) {
        super(message);
    }

    public NotEnoughBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
