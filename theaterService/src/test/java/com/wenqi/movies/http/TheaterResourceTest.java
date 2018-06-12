package com.wenqi.movies.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenqi.movies.entity.Theater;
import com.wenqi.movies.http.entity.HttpTheater;
import com.wenqi.movies.repository.TheaterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TheaterResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TheaterRepository theaterRepository;

    @Test
    public void testAddTheater() throws Exception {
        Theater newTheater = new Theater();
        newTheater.setName("testTheaterName");
        newTheater.setLocation("testTheaterLocation");
        doReturn(newTheater).when(theaterRepository).save(newTheater);

        MvcResult mockResponse = mockMvc.perform(post("/theaters/").accept(MediaType.APPLICATION_JSON_VALUE).content(getNewTheater())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/theater.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);

        verify(theaterRepository, times(1)).save(any(Theater.class));
        verifyNoMoreInteractions(theaterRepository);
    }

    @Test
    public void testGetTheaterById() throws Exception {
        Theater newTheater = new Theater();
        newTheater.setName("testTheaterName");
        newTheater.setLocation("testTheaterLocation");
        doReturn(newTheater).when(theaterRepository).findOne((long)123);

        MvcResult mockResponse = mockMvc.perform(get("/theaters/123").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/theater.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);

        verify(theaterRepository, times(1)).findOne((long)123);
        verifyNoMoreInteractions(theaterRepository);
    }

    @Test
    public void testGetTheaterByName() throws Exception {
        Theater newTheater = new Theater();
        newTheater.setName("testTheaterName");
        newTheater.setLocation("testTheaterLocation");
        doReturn(newTheater).when(theaterRepository).findByNameContaining("testTheaterName");

        MvcResult mockResponse = mockMvc.perform(get("/theaters/").param("name", "testTheaterName").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/theaters.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);

        verify(theaterRepository, times(1)).findByNameContaining("testTheaterName");
        verifyNoMoreInteractions(theaterRepository);
    }

    private String getNewTheater() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        HttpTheater theater = new HttpTheater();
        theater.id = 123;
        theater.name = "testTheaterName";
        theater.location = "testTheaterLocation";
        return mapper.writeValueAsString(theater);
    }
}
