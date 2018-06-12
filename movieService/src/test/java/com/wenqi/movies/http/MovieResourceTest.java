package com.wenqi.movies.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenqi.movies.entity.Movie;
import com.wenqi.movies.http.entity.HttpMovie;
import com.wenqi.movies.repository.MovieRepository;
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
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @Test
    public void testAddMovie() throws Exception {
        Movie newMovie = new Movie();
        newMovie.setName("testMovieName");
        newMovie.setIsPlaying(true);
        newMovie.setLength(99999);
        newMovie.setTrailerUrl("testUrl");
        newMovie.setGenre("testGenre");

        doReturn(newMovie).when(movieRepository).save(any(Movie.class));
        MvcResult mockResponse = mockMvc.perform(post("/movies/").accept(MediaType.APPLICATION_JSON_VALUE).content(getNewMovie())
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        String exceptedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movie.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(exceptedResponseBody, mvcResponse, true);

        verify(movieRepository, times(1)).save(any(Movie.class));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testGetMovieById()throws Exception {
        Movie mockMovie = new Movie();
        mockMovie.setName("testMovieName");
        mockMovie.setIsPlaying(true);
        mockMovie.setLength(99999);
        mockMovie.setTrailerUrl("testUrl");
        mockMovie.setGenre("testGenre");
        doReturn(mockMovie).when(movieRepository).findOne((long)0);

        MvcResult mockResponse = mockMvc.perform(get("/movies/0").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movie.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);

        verify(movieRepository, times(1)).findOne((long)0);
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testGetMoviesByName() throws Exception {
        Movie mockMovie = new Movie();
        mockMovie.setName("testMovieName");
        mockMovie.setIsPlaying(true);
        mockMovie.setLength(99999);
        mockMovie.setTrailerUrl("testUrl");
        mockMovie.setGenre("testGenre");
        doReturn(Arrays.asList(mockMovie)).when(movieRepository).findByNameContaining("testMovieName");

        MvcResult mockResponse = mockMvc.perform(get("/movies/name").param("name", "testMovieName")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String exceptedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(exceptedResponseBody, mvcResponse, true);

        verify(movieRepository, times(1)).findByNameContaining("testMovieName");
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    public void testGetMoviesByTheaterId() throws Exception {
        //TODO test get movies by theater id
//        MvcResult mockResponse = mockMvc.perform(get("/movies/theater").accept(MediaType.APPLICATION_JSON_VALUE).param("theater", String.valueOf(1))).andDo(print()).andReturn();
//        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
//        String exceptedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies.json")));
//        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
//        JSONAssert.assertEquals(exceptedResponseBody, mvcResponse, true);
    }

    @Test
    public void testGetMoviesByGenre() throws Exception {
        Movie mockMovie = new Movie();
        mockMovie.setName("testMovieName");
        mockMovie.setIsPlaying(true);
        mockMovie.setLength(99999);
        mockMovie.setTrailerUrl("testUrl");
        mockMovie.setGenre("testGenre");
        doReturn(Arrays.asList(mockMovie)).when(movieRepository).findByGenreContaining("testGenre");

        MvcResult mockResponse = mockMvc.perform(get("/movies/genre").param("genre", "testGenre").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
        assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        String exceptedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/movies.json")));
        String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
        JSONAssert.assertEquals(exceptedResponseBody, mvcResponse, true);

        verify(movieRepository, times(1)).findByGenreContaining("testGenre");
        verifyNoMoreInteractions(movieRepository);
    }

    private String getNewMovie() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        HttpMovie movie = new HttpMovie();
        movie.id = 123;
        movie.name = "testMovieName";
        movie.isPlaying = true;
        movie.length = 99999;
        movie.trailerUrl = "testUrl";
        movie.genre = "testGenre";
        return mapper.writeValueAsString(movie);
    }
}
