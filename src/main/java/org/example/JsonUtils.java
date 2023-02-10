package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.model.User;

public class JsonUtils {

    public static String convertToUserUsingJackson(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = new String();
        try {
            result = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static User convertJsonToUserUsingJackson(String user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User result = objectMapper.readValue(user, User.class);
        return result;
    }
}
