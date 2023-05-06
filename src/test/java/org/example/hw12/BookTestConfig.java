package org.example.hw12;

import org.example.hw12.model.Author;
import org.example.hw12.model.Book;
import org.example.hw12.repository.MariaBDBookRepository;
import org.example.hw12.repository.RedisRepository;
import org.example.hw12.service.BookService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@TestConfiguration
public class BookTestConfig {

    @Bean
    public RedisRepository redisRepository() {
        RedisRepository mock = mock(RedisRepository.class);
        return mock;
    }

    @Bean
    public MariaBDBookRepository mariaBDBookRepository() {
        MariaBDBookRepository mock = mock(MariaBDBookRepository.class);
        return mock;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
