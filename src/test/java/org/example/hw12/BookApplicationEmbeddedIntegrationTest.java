package org.example.hw12;

import com.wix.mysql.EmbeddedMysql;
import org.example.hw12.model.Author;
import org.example.hw12.repository.RedisRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import redis.embedded.RedisServer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {RedisEmbeddedTestConfig.class, MysqlEmbeddedTestConfig.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApplicationEmbeddedIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    @Qualifier(value = "testRedisTemplate")
    RedisTemplate redisTemplate;
    @Autowired
    RedisRepository redisRepository;
    @Autowired
    RedisServer redisServer;
    @Autowired
    EmbeddedMysql mysqlServer;
    @Autowired
    RestTemplate restTemplate;

    @BeforeAll
    void beforeAll() {
        this.redisServer.start();
    }
    @AfterAll
    void afterAll() {
        this.redisServer.stop();
        this.mysqlServer.stop();
    }

    @Test
    void testContextLoading() {
        assertThat(applicationContext, is(notNullValue()));
    }
    @Test
    void testMysqlEmbeddedServerNotNull() {
        assertThat(mysqlServer, is(notNullValue()));
    }

    @Test
    void test() {
        Author authorFromCache = Author.builder().id(555L).name("PushkinFromRedisEmbedded").birthDate("1799-06-06").build();
        redisTemplate.opsForValue().set("author_" + authorFromCache.getId(), authorFromCache);

        ResponseEntity<Author> response = restTemplate.getForEntity("http://localhost:" + port + "/books/author/555", Author.class);

        assertThat(response.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertThat(response.getHeaders().getContentType(), is(equalTo(MediaType.APPLICATION_JSON)));
        assertThat(response.getBody(), is(equalTo(authorFromCache)));
    }

    @Test
    void test2() {
        Author authorFromCache = Author.builder().id(666L).name("PushkinFromRedisEmbedded").birthDate("1799-06-06").build();
        redisRepository.saveAuthor(authorFromCache);

        ResponseEntity<Author> response = restTemplate.getForEntity("http://localhost:" + port + "/books/author/666", Author.class);

        assertThat(response.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertThat(response.getHeaders().getContentType(), is(equalTo(MediaType.APPLICATION_JSON)));
        assertThat(response.getBody(), is(equalTo(authorFromCache)));
    }
    @Test
    void testWhetAuthorIsPresentInEmbeddedDbOnly() {
        Author authorFromDb = Author.builder().id(777L).name("PushkinFromMysqlEmbedded").birthDate("1799-06-06").build();

        ResponseEntity<Author> response = restTemplate.getForEntity("http://localhost:" + port + "/books/author/777", Author.class);

        assertThat(response.getStatusCode(), is(HttpStatusCode.valueOf(200)));
        assertThat(response.getHeaders().getContentType(), is(equalTo(MediaType.APPLICATION_JSON)));
        assertThat(response.getBody(), is(equalTo(authorFromDb)));
    }
}
