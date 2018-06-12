package com.wenqi.movies.service.impl;

import com.wenqi.movies.entity.Theater;
import com.wenqi.movies.repository.TheaterRepository;
import com.wenqi.movies.service.TheaterService;
import com.wenqi.movies.service.exception.ErrorCode;
import com.wenqi.movies.service.exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public Theater addTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    @Override
    public void updateTheater(Theater theater) {
        theaterRepository.save(theater);
    }

    @Override
    public Theater getTheater(long id) {
        Theater found = theaterRepository.findOne(id);
        if (found == null) {
            throw new MovieException(ErrorCode.NOT_FOUND, "Theater not found, got theater failed");
        }
        return found;
    }

    @Override
    public Iterable<Theater> getTheatersByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new MovieException(ErrorCode.MISSING_DATA, "Missing data");
        }
        return theaterRepository.findByNameContaining(name);
    }

    @Override
    public void deleteTheaters(long theaterId) {
        Theater found = theaterRepository.findOne(theaterId);
        if (found == null) {
            throw new MovieException(ErrorCode.NOT_FOUND, "Movie not found, delete movie failed");
        }
        theaterRepository.delete(theaterId);
    }
}
