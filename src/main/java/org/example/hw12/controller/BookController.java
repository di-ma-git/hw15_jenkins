package org.example.hw12.controller;

import org.example.hw12.exeption.AuthorNotFoundException;
import org.example.hw12.exeption.BooksNotFoundException;
import org.example.hw12.model.Author;
import org.example.hw12.repository.RedisRepository;
import org.example.hw12.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final RedisRepository redisRepository;
    public BookController(BookService bookService, RedisRepository redisRepository) {
        this.bookService = bookService;
        this.redisRepository = redisRepository;
    }

    @GetMapping
    public ResponseEntity<?> allBooks() {
        try {
            return new ResponseEntity<>(bookService.getAllBooks(), OK);
        } catch (BooksNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Err!", BAD_REQUEST);
        }
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> authorDetails(@PathVariable String id) {
        try {
            Optional<Author> authorOptional = redisRepository.findAuthorById(id);
            if (authorOptional.isPresent()) {
                return new ResponseEntity<>(authorOptional.get(), OK);
            } else {
                Author author = bookService.getAuthorById(id);
                redisRepository.saveAuthor(author);
                return new ResponseEntity<>(author, OK);
            }
        } catch (AuthorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error!", BAD_REQUEST);
        }

    }


}
