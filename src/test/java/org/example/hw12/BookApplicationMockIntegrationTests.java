package org.example.hw12;

import jdk.jfr.ContentType;
import org.example.hw12.controller.BookController;
import org.example.hw12.exeption.AuthorNotFoundException;
import org.example.hw12.exeption.BooksNotFoundException;
import org.example.hw12.model.Author;
import org.example.hw12.model.Book;
import org.example.hw12.repository.MariaBDBookRepository;
import org.example.hw12.repository.RedisRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


//@SpringBootTest(classes = BookTestConfig.class, properties = "spring.main.allow-bean-definition-overriding=true")
@SpringBootTest(classes = BookTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApplicationMockIntegrationTests {
    @LocalServerPort
    private int port;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisRepository redisRepository;
    @Autowired
    MariaBDBookRepository bdBookRepository;
    @Autowired
    BookController bookController;

    Author authorFromCache = Author.builder().id(555L).name("PushkinFromRedisMock").birthDate("1799-06-06").build();
    Author authorFromDB = Author.builder().id(555L).name("PushkinFromDBMock").birthDate("1799-06-06").build();

    @AfterEach
    void resetMocks() {
        reset(redisRepository);
        reset(bdBookRepository);
    }

    @Test
    void testContextLoading() {
        assertThat(applicationContext, is(notNullValue()));
    }

    @Test
    void testControllerGetAuthorFromDBMock() {

        when(bdBookRepository.findAuthorById("555")).thenReturn(Optional.ofNullable(authorFromDB));
        ResponseEntity<Author> response = restTemplate.getForEntity("http://localhost:" + port + "/books/author/555", Author.class);

        assertThat(response.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertThat(response.getHeaders().getContentType(), is(equalTo(MediaType.APPLICATION_JSON)));
        assertThat(response.getBody(), is(equalTo(authorFromDB)));

    }

    @Test
    void testControllerWhenAuthorIsPresentInDBAndCache() {

        when(redisRepository.findAuthorById("555")).thenReturn(Optional.ofNullable(authorFromCache));
        when(bdBookRepository.findAuthorById("555")).thenReturn(Optional.ofNullable(authorFromDB));
        ResponseEntity<Author> response = restTemplate.getForEntity("http://localhost:" + port + "/books/author/555", Author.class);

        assertThat(response.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertThat(response.getHeaders().getContentType(), is(equalTo(MediaType.APPLICATION_JSON)));
        assertThat(bookController.authorDetails("555").getBody(), is(equalTo(authorFromCache)));
    }


    @Test
    void testControllerWhenNoConnectionToDB() {

        try {
            when(redisRepository.findAuthorById(anyString())).thenReturn(Optional.ofNullable(null));
            when(bdBookRepository.findAuthorById(anyString())).thenReturn(null);
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/books/author/555", String.class);

            assertThat(response.getStatusCode(), is(equalTo(HttpStatusCode.valueOf(400))));
            assertThat(response.getBody(), is(equalTo("Error")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testControllerExceptionAuthorNotFound() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/books/author/999", String.class);

            assertThat(response.getStatusCode(), is(equalTo(HttpStatusCode.valueOf(404))));
            assertThat(response.getBody(), is(equalTo("Author not found")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testControllerGetAllBooks() {
        List<Book> testBooks = Arrays.asList(Book.builder()
                        .id(333L)
                        .bookName("First Book")
                        .author("Author1")
                        .description("description1")
                        .publishDate("1111-11-11")
                        .build(),
                Book.builder()
                        .id(444L)
                        .bookName("Second Book")
                        .author("Author2")
                        .description("description2")
                        .publishDate("2222-22-22")
                        .build());

        when(bdBookRepository.findAllBooks()).thenReturn(Optional.ofNullable(testBooks));
        ResponseEntity<List<Book>> response = restTemplate.exchange("http://localhost:" + port + "/books",HttpMethod.GET, null,  new ParameterizedTypeReference<List<Book>>() {});

        assertThat(response.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertThat(response.getHeaders().getContentType(), is(equalTo(MediaType.APPLICATION_JSON)));
        assertThat(response.getBody(), is(equalTo(testBooks)));

    }

    @Test
    void testControllerExceptionBooksNotFound() throws Exception {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/books", String.class);

            assertThat(response.getStatusCode(), is(equalTo(HttpStatusCode.valueOf(404))));
            assertThat(response, is(equalTo("No one book found! Database is empty!")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
