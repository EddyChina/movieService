package com.wenqi.movies.entity;

public interface Movie {
    long getId();

    String getName();

    boolean getIsPlaying();

    long getLength();

    String getTrailerUrl();

    String getGenre();
}
