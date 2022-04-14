package ru.amlet.exception;

import java.util.function.Supplier;

public class BookException extends RuntimeException {

    public BookException(String message) {
        super(message);
    }

}
