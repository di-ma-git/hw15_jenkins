package org.example.hw12.repository;

import org.example.hw12.model.Author;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveAuthor(Author author) {
        redisTemplate.opsForValue().set(String.valueOf(author.getId()), author);
    }

    public Author findAuthorById(String key) {
        return (Author) redisTemplate.opsForValue().get(key);
    }
}
