package com.gjohnson.homesite.database;

public class InvalidQueryException extends RuntimeException {

    public InvalidQueryException(String message) {
        super(message);
    }

    public InvalidQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public static InvalidQueryException selectException() {
        return new InvalidQueryException("No select clause specified.");
    }

    public static InvalidQueryException fromException() {
        return new InvalidQueryException("No from clause specified.");
    }

    public static InvalidQueryException joinException() {
        return new InvalidQueryException("No join clauses specified.");
    }
}
