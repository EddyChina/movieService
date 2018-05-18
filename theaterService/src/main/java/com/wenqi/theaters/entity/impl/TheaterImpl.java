package com.wenqi.theaters.entity.impl;

import com.wenqi.theaters.entity.Theater;

public class TheaterImpl implements Theater {
    private long id;
    private String name;
    private String location;

    public TheaterImpl() {

    }

    public TheaterImpl(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
