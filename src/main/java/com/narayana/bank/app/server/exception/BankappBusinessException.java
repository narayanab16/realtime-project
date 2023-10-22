package com.narayana.bank.app.server.exception;

public class BankappBusinessException extends Exception{
    public BankappBusinessException() {
    }

    public BankappBusinessException(String message) {
        super(message);
    }

    public BankappBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankappBusinessException(Throwable cause) {
        super(cause);
    }

    public BankappBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
