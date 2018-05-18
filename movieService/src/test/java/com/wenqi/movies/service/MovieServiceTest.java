package com.wenqi.movies.service;

import com.wenqi.movies.entity.Movie;
import com.wenqi.movies.entity.impl.MovieImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieServiceTest {
    @Autowired
    private MovieService movieService;

    @Test
    public void testAddMovie() {
        Movie movieToCreate = new MovieImpl(0);
        assertThat(movieService.addMovie(movieToCreate)).isEqualTo(movieToCreate);
    }

    @Test
    public void testGetMovie() {
        Movie movieGet = movieService.getMovie(0);

        assertThat(movieGet instanceof Movie).isTrue();
        assertThat(movieGet.getId()).isEqualTo(0);
        assertThat(movieGet.getName()).isNull();
        assertThat(movieGet.getIsPlaying()).isFalse();
        assertThat(movieGet.getLength()).isEqualTo(0);
        assertThat(movieGet.getTrailerUrl()).isNull();
        assertThat(movieGet.getGenre()).isNull();
    }
}
