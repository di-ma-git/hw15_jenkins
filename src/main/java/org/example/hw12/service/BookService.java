package org.example.hw12.service;

import org.example.hw12.exeption.AuthorNotFoundException;
import org.example.hw12.exeption.BooksNotFoundException;
import org.example.hw12.model.Author;
import org.example.hw12.model.Book;
import org.example.hw12.repository.MariaBDBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class BookService {

    @Autowired
    private MariaBDBookRepository bookRepository;


    public List<Book> getAllBooks() throws BooksNotFoundException {
        Optional<List<Book>> optionalBookList = bookRepository.findAllBooks();
        if (optionalBookList.isEmpty()) {
            throw new BooksNotFoundException("No one book found!");
        } else {
            return optionalBookList.get();
        }


    }

    public Author getAuthorById(String id) throws AuthorNotFoundException {
        Optional<Author> authorOptional = bookRepository.findAuthorById(id);
        if (authorOptional.isEmpty()) {
            throw new AuthorNotFoundException("Author not found");
        } else {
            return authorOptional.get();
        }
    }
}

