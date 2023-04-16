package org.example.hw12.exeption;

public class BookAlreadyExistException extends Exception {

    public BookAlreadyExistException(String message) {
        super(message);
    }
}
