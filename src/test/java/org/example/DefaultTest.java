package org.example;

import org.example.tcphttp.UserApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DefaultTest {

    UserApi userApi;

    @BeforeAll
    public void init() {
        userApi = UserApi.getInstance();
    }

    @Test
    public void simpeTest(){
        List<String> one = new ArrayList<>();
        List<String> two = new ArrayList<>();

        one.add("2");
        one.add("1");

        two.add("1");
        two.add("2");

        assertThat(one, containsInAnyOrder(two.toArray()));
        assertThat(two, containsInAnyOrder(one.toArray()));
    }

}
