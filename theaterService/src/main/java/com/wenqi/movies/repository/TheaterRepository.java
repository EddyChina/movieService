package com.wenqi.movies.repository;

import com.wenqi.movies.entity.Theater;
import org.springframework.data.repository.CrudRepository;

public interface TheaterRepository extends CrudRepository<Theater, Long> {
    Iterable<Theater> findByNameContaining(String name);

    Iterable<Theater> findByLocationContaining(String location);


}
