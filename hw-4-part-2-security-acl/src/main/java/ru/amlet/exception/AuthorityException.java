package ru.amlet.exception;

public class AuthorityException extends RuntimeException {

    public AuthorityException() {
    }

    public AuthorityException(String message) {
        super(message);
    }
}
