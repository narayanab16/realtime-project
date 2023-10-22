package com.narayana.bank.app.server.exception;

public class BankappSystemException extends Exception {
    public BankappSystemException() {
    }

    public BankappSystemException(String message) {
        super(message);
    }

    public BankappSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankappSystemException(Throwable cause) {
        super(cause);
    }

    public BankappSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
