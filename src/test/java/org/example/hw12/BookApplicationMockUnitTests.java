package org.example.hw12;

import org.example.hw12.controller.BookController;
import org.example.hw12.exeption.AuthorNotFoundException;
import org.example.hw12.model.Author;
import org.example.hw12.repository.MariaBDBookRepository;
import org.example.hw12.repository.RedisRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = BookTestConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookApplicationMockUnitTests {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    RedisRepository redisRepository;
    @Autowired
    MariaBDBookRepository bdBookRepository;
    @Autowired
    BookController bookController;

    @AfterEach
    void resetMocks() {
        reset(redisRepository);
        reset(bdBookRepository);
    }

    @Order(1)
    @Test
    void testContextLoaded() {
        assertThat(applicationContext, is(notNullValue()));
    }

    @Order(2)
    @Test
    void testRedisRepository() {
        Author author = Author.builder().id(555L).name("PushkinFromRedisMock").birthDate("1799-06-06").build();

        when(redisRepository.findAuthorById("555")).thenReturn(Optional.ofNullable(author));

        assertThat(bookController.authorDetails("555").getBody(), is(equalTo(author)));
    }

    @Order(3)
    @Test
    void testDBRepository() {
        Author author = Author.builder().id(555L).name("PushkinFromDBMock").birthDate("1799-06-06").build();

        when(bdBookRepository.findAuthorById("555")).thenReturn(Optional.ofNullable(author));

        assertThat(bookController.authorDetails("555").getBody(), is(equalTo(author)));
    }

}
