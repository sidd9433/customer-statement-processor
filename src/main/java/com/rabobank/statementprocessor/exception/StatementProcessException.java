package com.rabobank.statementprocessor.exception;

public class StatementProcessException extends Exception {
    public StatementProcessException(String message) {
        super(message);
    }

    public StatementProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
