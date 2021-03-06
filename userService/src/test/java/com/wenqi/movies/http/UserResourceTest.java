package com.wenqi.movies.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenqi.movies.http.entity.HttpUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUser() throws Exception {
        MvcResult mockResponse = mockMvc.perform(get("/movies").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies-list.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
    }

    @Test
    public void testGetUserById() throws Exception {
        MvcResult mockResponse = mockMvc.perform(get("/movies/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies-id.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
    }

    @Test
    public void testCreateUser() throws Exception {
        MvcResult mockResponse = mockMvc.perform(post("/movies").accept(MediaType.APPLICATION_JSON).content(getNewUser())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());

        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies-create.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
    }

    private String getNewUser() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        HttpUser user = new HttpUser();
        user.id = 123;
        user.userName = "foo";
        user.firstName = "David";
        user.lastName = "Brown";
        user.pin = "1234";
        return mapper.writeValueAsString(user);
    }
}
