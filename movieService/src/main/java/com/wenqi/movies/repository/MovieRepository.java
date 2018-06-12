package com.wenqi.movies.repository;

import com.wenqi.movies.entity.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

    Iterable<Movie> findByNameContaining(String name);

    Iterable<Movie> findByIsPlaying(boolean isPlaying);

    Iterable<Movie> findByLength(long length);

    Iterable<Movie> findByTrailerUrl(String trailerUrl);

    Iterable<Movie> findByGenreContaining(String genre);

}
