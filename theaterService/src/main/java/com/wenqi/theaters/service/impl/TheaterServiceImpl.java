package com.wenqi.theaters.service.impl;

import com.wenqi.theaters.entity.Theater;
import com.wenqi.theaters.entity.impl.TheaterImpl;
import com.wenqi.theaters.service.TheaterService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Override
    public Theater addTheater(Theater theater) {
        return theater;
    }

    @Override
    public void updateTheater(Theater theater) {

    }

    @Override
    public Theater getTheater(long id) {
        return new TheaterImpl(id);
    }

    @Override
    public Iterable<Theater> getTheaters(String name) {
        List<Theater> list = new ArrayList<>();
        list.add(new TheaterImpl(1));
        list.add(new TheaterImpl(2));
        return list;
    }

    @Override
    public void deleteTheaters(Theater theater) {

    }
}
