package org.example.hw12.repository;

import lombok.AllArgsConstructor;
import org.example.hw12.model.Author;
import org.example.hw12.model.Book;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class MariaBDBookRepository {
    private DataSource dataSource;

    public Optional<List<Book>> findAllBooks() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT book_id, book_name," +
                    "book_description, YEAR(book_publish_date), author_name, books.author_id FROM books.books\n" +
                    "JOIN books.authors\n" +
                    "ON books.author_id = authors.author_id");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getLong("book_id"))
                        .bookName(resultSet.getString("book_name"))
                        .description(resultSet.getString("book_description"))
                        .publishDate(resultSet.getString("YEAR(book_publish_date)"))
                        .author(resultSet.getString("author_name"))
                        .authorId(resultSet.getLong("author_id"))
                        .build();
                books.add(book);
            }
            return Optional.ofNullable(books);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<Author> findAuthorById(String id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books.authors WHERE author_id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Author author = null;

            while (resultSet.next()) {
                author = Author.builder()
                        .id(resultSet.getLong("author_id"))
                        .name(resultSet.getString("author_name"))
                        .birthDate(String.valueOf(resultSet.getDate("author_birth_date")))
                        .build();
            }
            return Optional.ofNullable(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
