package com.wenqi.theaters.service;

import com.wenqi.theaters.entity.Theater;

public interface TheaterService {
    Theater addTheater(Theater theater);

    void updateTheater(Theater theater);

    Theater getTheater(long id);

    Iterable<Theater> getTheaters(String name);

    void deleteTheaters(Theater theater);
}
