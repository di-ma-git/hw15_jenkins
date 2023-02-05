package org.example;

import com.jayway.jsonpath.JsonPath;
import org.example.model.User;
import org.junit.jupiter.api.Test;

import static org.example.JsonUtils.convertJsonToUserUsingJackson;
import static org.example.JsonUtils.convertToUserUsingJackson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JsonUtilTest extends DefaultTest {

    @Test
    public void convertToString(){
        User user = new User("John",33);
        String validJsonString =  convertToUserUsingJackson(user);
        String cutName = JsonPath.parse(validJsonString).read("$.name");
        Integer id = JsonPath.parse(validJsonString).read("$.id");

        assertThat("John", equalTo(cutName));
        assertThat(33, equalTo(id));
    }

    @Test
    public void convertToObject(){
        String validJson = "{\"name\":\"James\", \"id\":31}";
        User parsedUser =  convertJsonToUserUsingJackson(validJson);

        assertThat("James", equalTo(parsedUser.getName()));
        assertThat(31, equalTo(parsedUser.getId()));
    }

}
