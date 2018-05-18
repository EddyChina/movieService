package com.wenqi.theaters.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenqi.theaters.http.entity.HttpTheater;
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
public class TheaterResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddTheater() throws Exception {
        MvcResult mockResponse = mockMvc.perform(post("/theaters/").accept(MediaType.APPLICATION_JSON_VALUE).content(getNewTheater())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/theater-create.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
    }

    @Test
    public void testGetTheater() throws Exception {
        MvcResult mockResponse = mockMvc.perform(get("/theaters/0").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String expectedRespsonseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/theater-id.json")));
        String mvcRespnse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedRespsonseBody, mvcRespnse, true);
    }

    @Test
    public void testGetTheaters() throws Exception {
        MvcResult mockResponse = mockMvc.perform(get("/theaters/").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/theater-list.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
    }

    private String getNewTheater() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        HttpTheater user = new HttpTheater();
        user.id = 123;
        user.name = "AMC";
        user.location = "3111 Mission College Blvd, Santa Clara, CA 95054";
        return mapper.writeValueAsString(user);
    }
}
