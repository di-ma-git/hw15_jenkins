package org.example.hw12.exeption;

public class BooksNotFoundException extends Exception {
    public BooksNotFoundException(String message) {
        super(message);
    }
}
