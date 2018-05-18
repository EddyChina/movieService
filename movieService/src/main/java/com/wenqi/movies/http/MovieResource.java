package com.wenqi.movies.http;

import com.wenqi.movies.entity.Movie;
import com.wenqi.movies.entity.impl.MovieImpl;
import com.wenqi.movies.http.entity.HttpMovie;
import com.wenqi.movies.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieResource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpMovie> createMovie(@RequestBody HttpMovie newMovie) {
        Movie movieToCreate = convert(newMovie);
        logger.info("Create movie: " + movieToCreate);
        Movie addedMovie = movieService.addMovie(movieToCreate);
        return new ResponseEntity<>(new HttpMovie(addedMovie), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public ResponseEntity<HttpMovie> getMovie(@PathVariable("movieId") long movieId) {
        logger.info("Getting movie by id: " + movieId);
        Movie movie = movieService.getMovie(movieId);
        return new ResponseEntity<>(new HttpMovie(movie), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/name")
    public ResponseEntity<List<HttpMovie>> getMoviesByName(@RequestParam(value = "name", required = false) String name) {
        logger.info("Movies search by name = " + name);
        Iterable<Movie> found = movieService.getMoviesByName(name);
        List<HttpMovie> returnList = new ArrayList<>();
        for (Movie movie : found) {
            returnList.add(new HttpMovie(movie));
        }
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/theater")
    public ResponseEntity<List<HttpMovie>> getMoviesByTheaterId(@RequestParam(value = "theater", required = false) long theaterId) {
        logger.info("Movies search by name = " + theaterId);
        Iterable<Movie> found = movieService.getMoviesByTheaterId(theaterId);
        List<HttpMovie> returnList = new ArrayList<>();
        for (Movie movie : found) {
            returnList.add(new HttpMovie(movie));
        }
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/genre")
    public ResponseEntity<List<HttpMovie>> getMoviesByGenre(@RequestParam(value = "genre", required = false) String genre) {
        logger.info("Movies search by name = " + genre);
        Iterable<Movie> found = movieService.getMoviesByGenre(genre);
        List<HttpMovie> returnList = new ArrayList<>();
        for (Movie movie : found) {
            returnList.add(new HttpMovie(movie));
        }
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    /**
     * Not pushing this into business layer. Could be a util as well
     *
     * @param httpMovie
     * @return
     */
    private Movie convert(HttpMovie httpMovie) {
        MovieImpl movie = new MovieImpl();
        movie.setName(httpMovie.name);
        movie.setIsPlaying(httpMovie.isPlaying);
        movie.setLength(httpMovie.length);
        movie.setTrailerUrl(httpMovie.trailerUrl);
        movie.setGenre(httpMovie.genre);
        return movie;
    }
}
