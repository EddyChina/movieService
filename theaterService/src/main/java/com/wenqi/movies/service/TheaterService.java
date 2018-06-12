package com.wenqi.movies.service;

import com.wenqi.movies.entity.Theater;

public interface TheaterService {
    Theater addTheater(Theater theater);

    void updateTheater(Theater theater);

    Theater getTheater(long id);

    Iterable<Theater> getTheatersByName(String name);

    void deleteTheaters(long theaterId);
}
