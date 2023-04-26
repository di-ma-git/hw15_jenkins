package org.example.hw12.repository;

import org.example.hw12.model.Author;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {
    private final String AUTHOR_KEY_PREFIX = "author_";
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveAuthor(Author author) {
        redisTemplate.opsForValue().set(AUTHOR_KEY_PREFIX + author.getId(), author, 1, TimeUnit.HOURS);
    }

    public Optional<Author> findAuthorById(String key) {
        return Optional.ofNullable((Author) redisTemplate.opsForValue().get(AUTHOR_KEY_PREFIX + key));
    }
}
