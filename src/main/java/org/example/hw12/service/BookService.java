package org.example.hw12.service;

import org.example.hw12.model.Author;
import org.example.hw12.model.Book;
import org.example.hw12.repository.MariaBDBookRepository;
import org.example.hw12.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    MariaBDBookRepository bookRepository;
    @Autowired
    RedisRepository redisRepository;

//    public void addBook(Book book) throws BookAlreadyExistException {
//        if (bookRepository.findByBookName(book.getBookName()) != null) {
//            throw new BookAlreadyExistException("Such book already exist!");
//        }
//    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllBooks();
    }

    public Author getAuthor(String id) {
        if (redisRepository.findAuthorById(id) != null) {
            return redisRepository.findAuthorById(id);
        } else {
            Author author = bookRepository.findAuthorById(id);
            redisRepository.saveAuthor(author);
            return author;
        }
    }
}
