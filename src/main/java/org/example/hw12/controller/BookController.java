package org.example.hw12.controller;

import org.example.hw12.exeption.BookAlreadyExistException;
import org.example.hw12.model.Book;
import org.example.hw12.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> allBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), OK);

    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> authorDetails(@PathVariable String id) {
        return new ResponseEntity<>(bookService.getAuthor(id), OK);
    }





}
