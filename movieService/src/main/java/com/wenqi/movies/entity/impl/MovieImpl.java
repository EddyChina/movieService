package com.wenqi.movies.entity.impl;

import com.wenqi.movies.entity.Movie;

public class MovieImpl implements Movie {
    private long id;
    private String name;
    private boolean isPlaying;
    private long length;
    private String trailerUrl;
    private String genre;


    public MovieImpl() {

    }

    public MovieImpl(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    @Override
    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    @Override
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
