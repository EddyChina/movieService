package com.wenqi.movies.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenqi.movies.http.entity.HttpMovie;
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
public class MovieResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMovie()throws Exception {
        MvcResult mockResponse = mockMvc.perform(get("/movies/0").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies-id.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
    }

    @Test
    public void testAddMovie() throws Exception {
        MvcResult mockResponse = mockMvc.perform(post("/movies/").accept(MediaType.APPLICATION_JSON_VALUE).content(getNewMovie())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        String exceptedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies-create.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(exceptedResponseBody, mvcResponse, true);
    }

    @Test
    public void testGetMoviesByName() throws Exception {
        MvcResult mockResponse = mockMvc.perform(get("/movies/name").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String exceptedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies-name.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(exceptedResponseBody, mvcResponse, true);
    }

    @Test
    public void testGetMoviesByTheaterId() throws Exception {
        MvcResult mockResponse = mockMvc.perform(get("/movies/theater").accept(MediaType.APPLICATION_JSON_VALUE).param("theater", String.valueOf(1))).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String exceptedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies-theaterId.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(exceptedResponseBody, mvcResponse, true);
    }

    @Test
    public void testGetMoviesByGenre() throws Exception {
        MvcResult mockResponse = mockMvc.perform(get("/movies/genre").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String exceptedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies-genre.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(exceptedResponseBody, mvcResponse, true);
    }

    private String getNewMovie() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        HttpMovie movie = new HttpMovie();
        movie.id = 123;
        movie.name = "Avengers: Infinity War";
        movie.isPlaying = true;
        movie.length = 7200;
        movie.trailerUrl = "https://youtu.be/6ZfuNTqbHE8";
        movie.genre = "Fantasy/Science Fiction Film";
        return mapper.writeValueAsString(movie);
    }
}
