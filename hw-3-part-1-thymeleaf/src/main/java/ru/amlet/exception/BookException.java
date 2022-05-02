package ru.amlet.exception;

public class BookException extends RuntimeException {

    public BookException() {
    }

    public BookException(String message) {
        super(message);
    }

}
