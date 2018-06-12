package com.wenqi.movies.http.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wenqi.movies.entity.Theater;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class HttpTheater {
    public long id;
    public String name;
    public String location;

    public HttpTheater() {

    }

    public HttpTheater(Theater theater) {
        this.id = theater.getId();
        this.name = theater.getName();
        this.location = theater.getLocation();
    }
}
