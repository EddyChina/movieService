package com.wenqi.movies.service;

import com.wenqi.movies.entity.Movie;

public interface MovieService {
    Movie addMovie(Movie movie);

    void updateMovie(Movie movie);

    Movie getMovie(long movieId);

    Iterable<Movie> getMoviesByName(String name);

    void deleteMovie(long movieId);

    boolean isPlaying(Movie movie);

    long getLength(Movie movie);

    String getTrailerUrl(Movie movie);

    Iterable<Movie> getMoviesByTheaterId(long TheaterId);

    Iterable<Movie> getMoviesByGenre(String genre);
}
