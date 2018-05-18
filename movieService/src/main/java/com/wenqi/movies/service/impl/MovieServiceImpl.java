package com.wenqi.movies.service.impl;

import com.wenqi.movies.entity.Movie;
import com.wenqi.movies.entity.impl.MovieImpl;
import com.wenqi.movies.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Override
    public Movie addMovie(Movie movie) {
        return movie;
    }

    @Override
    public void updateMovie(Movie movie) {

    }

    @Override
    public Movie getMovie(long movieId) {
        return new MovieImpl(movieId);
    }

    @Override
    public Iterable<Movie> getMoviesByName(String name) {
        List<Movie> list = new ArrayList<>();
        list.add(new MovieImpl(1));
        list.add(new MovieImpl(2));
        list.add(new MovieImpl(3));
        return list;
    }

    @Override
    public void deleteMovie(Movie movie) {

    }

    @Override
    public boolean isPlaying(Movie movie) {
        return movie.getIsPlaying();
    }

    @Override
    public long getLength(Movie movie) {
        return movie.getLength();
    }

    @Override
    public String getTrailerUrl(Movie movie) {
        return movie.getTrailerUrl();
    }

    @Override
    public Iterable<Movie> getMoviesByTheaterId(long TheaterId) {
        List<Movie> list = new ArrayList<>();
        list.add(new MovieImpl(1));
        list.add(new MovieImpl(2));
        return list;
    }

    @Override
    public Iterable<Movie> getMoviesByGenre(String genre) {
        List<Movie> list = new ArrayList<>();
        list.add(new MovieImpl(1));
        list.add(new MovieImpl(2));
        return list;
    }

}
