package com.wenqi.movies.service;

import com.wenqi.movies.entity.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @Test
    public void testAddAndGetAndDeleteMovie() {
        Movie newMovie = new Movie();
        newMovie.setName("testMovieName");
        newMovie.setIsPlaying(true);
        newMovie.setLength(99999);
        newMovie.setTrailerUrl("testUrl");
        newMovie.setGenre("testGenre");

        Movie addedMovie = movieService.addMovie(newMovie);
        logger.info("Movie added " + addedMovie);
        assertThat(addedMovie.getId()).isNotEqualTo(0);//this should have been automatically generated so not zero
        assertThat(addedMovie.getName()).isEqualTo(newMovie.getName());
        assertThat(addedMovie.getIsPlaying()).isEqualTo(newMovie.getIsPlaying());
        assertThat(addedMovie.getLength()).isEqualTo(newMovie.getLength());
        assertThat(addedMovie.getTrailerUrl()).isEqualTo(newMovie.getTrailerUrl());
        assertThat(addedMovie.getGenre()).isEqualTo(newMovie.getGenre());

        Movie found = movieService.getMovie(newMovie.getId());
        logger.info("Movie got " + found);
        assertThat(found.getId()).isEqualTo(addedMovie.getId());
        assertThat(found.getName()).isEqualTo(addedMovie.getName());
        assertThat(found.getIsPlaying()).isEqualTo(addedMovie.getIsPlaying());
        assertThat(found.getLength()).isEqualTo(addedMovie.getLength());
        assertThat(found.getTrailerUrl()).isEqualTo(addedMovie.getTrailerUrl());
        assertThat(found.getGenre()).isEqualTo(addedMovie.getGenre());

        movieService.deleteMovie(addedMovie.getId());
        logger.info("Movie deleted " + addedMovie.getId());
    }

    @Test
    public void testSearchMovieByName() {
        Movie newMovie = new Movie();
        newMovie.setName("testMovieName");
        newMovie.setIsPlaying(true);
        newMovie.setLength(99999);
        newMovie.setTrailerUrl("testUrl");
        newMovie.setGenre("testGenre");

        Movie addedMovie = movieService.addMovie(newMovie);
        logger.info("Movie added " + addedMovie);

        Iterable<Movie> found = movieService.getMoviesByName("testMovieName");
        logger.info("Movie got " + found);
        found.forEach(movie -> assertThat(movie.getName()).isEqualTo("testMovieName"));

        movieService.deleteMovie(addedMovie.getId());
        logger.info("Movie deleted " + addedMovie.getId());
    }

    @Test
    public void testSearchMovieByGenre() {
        Movie newMovie = new Movie();
        newMovie.setName("testMovieName");
        newMovie.setIsPlaying(true);
        newMovie.setLength(99999);
        newMovie.setTrailerUrl("testUrl");
        newMovie.setGenre("testGenre");

        Movie addedMovie = movieService.addMovie(newMovie);
        logger.info("Movie added " + addedMovie);

        Iterable<Movie> found = movieService.getMoviesByGenre("g");
        logger.info("Movie got " + found);
        if (!found.iterator().hasNext()) {
            logger.info("test");
        }
        found.forEach(m -> logger.info("movie got " + m));
        found.forEach(movie -> assertThat(movie.getGenre()).isEqualTo("testGenre"));

        movieService.deleteMovie(addedMovie.getId());
        logger.info("Movie deleted " + addedMovie.getId());
    }

}
