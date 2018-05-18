package com.wenqi.movies.http.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wenqi.movies.entity.Movie;

/**
 * Select fields we want exposed to the REST layer. HTTP separation from business/data layer.
 *
 * @author wenqi
 *
 */

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class HttpMovie {
    public long id;
    public String name;
    public boolean isPlaying;
    public long length;
    public String trailerUrl;
    public String genre;

    public HttpMovie() {

    }

    public HttpMovie(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        this.isPlaying = movie.getIsPlaying();
        this.length = movie.getLength();
        this.trailerUrl = movie.getTrailerUrl();
        this.genre = movie.getGenre();
    }
}
