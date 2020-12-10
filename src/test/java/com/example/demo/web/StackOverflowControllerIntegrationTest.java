package com.example.demo.web;

import com.example.demo.DemoApplication;
import com.example.demo.model.StackOverflowWebSite;
import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.stream.Collectors.collectingAndThen;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
class StackOverflowControllerIntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void before(){
        mongoTemplate.dropCollection(StackOverflowWebSite.class);
        mongoTemplate.save(new StackOverflowWebSite("website1", "", "", "", ""));
        mongoTemplate.save(new StackOverflowWebSite("website2", "", "", "", ""));

    }

    @Test
    void getListOfProviders() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("/api/stackoverflow", List.class);
       assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
       List<LinkedHashMap<String, String>> actual = responseEntity.getBody();

        assert actual != null;
        assertThat(actual.size(), is(2));
        List<String> id = new ArrayList<>();
        for (LinkedHashMap<String, String> resp : actual) {
            id.add(resp.get("id"));
        }
        assertThat(id, containsInAnyOrder("website1", "website2")); /*/**/
    }
}