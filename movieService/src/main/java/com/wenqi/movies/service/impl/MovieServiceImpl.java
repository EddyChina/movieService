package com.wenqi.movies.service.impl;

import com.wenqi.movies.entity.Movie;
import com.wenqi.movies.repository.MovieRepository;
import com.wenqi.movies.service.MovieService;
import com.wenqi.movies.service.exception.ErrorCode;
import com.wenqi.movies.service.exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Iterator;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(long movieId) {
        Movie found = movieRepository.findOne(movieId);
        if (found == null) {
            throw new MovieException(ErrorCode.NOT_FOUND, "Movie not found, get movie failed");
        }
        return found;
    }

    @Override
    public Iterable<Movie> getMoviesByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new MovieException(ErrorCode.MISSING_DATA, "Missing data");
        }
        return movieRepository.findByNameContaining(name);
    }

    @Override
    public void deleteMovie(long movieId) {
        Movie found = movieRepository.findOne(movieId);
        if (found == null) {
            throw new MovieException(ErrorCode.NOT_FOUND, "Movie not found, delete movie failed");
        }
        movieRepository.delete(movieId);
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
        // TODO get movies by theater id
        return new Iterable<Movie>() {
            @Override
            public Iterator<Movie> iterator() {
                return null;
            }
        };
    }

    @Override
    public Iterable<Movie> getMoviesByGenre(String genre) {
        Iterable<Movie> found = movieRepository.findByGenreContaining(genre);
        if (found == null) {
            throw new MovieException(ErrorCode.NOT_FOUND, "Movies by genre not found");
        }
        return found;
    }

}
