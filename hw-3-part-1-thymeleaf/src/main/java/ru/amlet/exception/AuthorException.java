package ru.amlet.exception;

public class AuthorException extends RuntimeException {

    public AuthorException() {
    }

    public AuthorException(String message) {
        super(message);
    }
}
