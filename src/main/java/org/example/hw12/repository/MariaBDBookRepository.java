package org.example.hw12.repository;

import org.example.hw12.model.Author;
import org.example.hw12.model.Book;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
//@ConfigurationProperties(prefix = "mariabdrepo")
public class MariaBDBookRepository {
    private String DRIVER;
//    @Value("${URL}")
//    private String URL = "jdbc:mariadb://185.106.92.133:3310/books";
    private String URL = "jdbc:mariadb://127.0.0.1:3306/books_bd";
//    @Value("${USERNAME}")
    private String USERNAME = "root";
//    @Value("${PASS}")
    private String PASS = "vY3qS4uW9atT";

    public List<Book> findAllBooks() {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASS)) {
//            Class.forName(DRIVER);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT book_id, book_name," +
                    "book_description, YEAR(book_publish_date), author_name, books.author_id FROM books_bd.books\n" +
                    "JOIN books_bd.authors\n" +
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
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Author findAuthorById(String id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASS)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books_db.authors WHERE author_id = ?");
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
            return author;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public MariaBDBookRepository(String url, String username, String pass) {
////        DRIVER = driver;
//        URL = url;
//        USERNAME = username;
//        PASS = pass;
//    }
//
//    public MariaBDBookRepository() {}
}
