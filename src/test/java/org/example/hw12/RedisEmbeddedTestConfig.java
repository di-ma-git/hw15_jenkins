package org.example.hw12;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;
import redis.embedded.RedisServer;

import java.io.IOException;

@TestConfiguration
public class RedisEmbeddedTestConfig {
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value("${spring.redis.host}")
    private String redisHost;
//    RedisServer redisServer;

    @Bean
    public RedisServer redisServer() throws IOException {
        RedisServer redisServer = new RedisServer(redisPort);
//        redisServer.start();
        return redisServer;
    }
    @Bean(name = "testRedisConnectionFactory")
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }


    @Bean(name = "testRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier(value = "testRedisConnectionFactory") LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

//    @PostConstruct
//    public void postConstruct() {
//        redisServer.start();
//    }
//
//    @PreDestroy
//    public void preDestroy() {
//        redisServer.stop();
//    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
