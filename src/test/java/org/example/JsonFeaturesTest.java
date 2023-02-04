package org.example;

import com.jayway.jsonpath.JsonPath;
import org.example.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JsonFeaturesTest extends DefaultTest {


    JsonFeatures jsonFeatures;

    @BeforeAll
    public void init(){
        jsonFeatures = new JsonFeatures();
    }

    @Test
    public void convertToString(){
        User user = new User("John",33);
        String validJsonString =  jsonFeatures.convertToUserUsingJackson(user);
        String cutName = JsonPath.parse(validJsonString).read("$.name");
        Integer id = JsonPath.parse(validJsonString).read("$.id");
        assertThat("John", equalTo(cutName));
        assertThat(33, equalTo(id));
    }

    @Test
    public void convertToObject(){
        String validJson = "{\"name\":\"James\", \"id\":31}";
        User parsedUser =  jsonFeatures.convertJsonToUserUsingJackson(validJson);
        assertThat("James", equalTo(parsedUser.getName()));
        assertThat(31, equalTo(parsedUser.getId()));
    }

}
