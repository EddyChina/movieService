package com.wenqi.movies.service;

import com.wenqi.movies.entity.Theater;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheaterServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TheaterService theaterService;

    @Test
    public void testAddAndGetAndDeleteTheater() {
        Theater newTheater = new Theater();
        newTheater.setName("testTheaterName");
        newTheater.setLocation("testTheaterLocation");

        Theater addedTheater = theaterService.addTheater(newTheater);
        logger.info("Theater added " + addedTheater);
        assertThat(addedTheater.getId()).isNotEqualTo(0);
        assertThat(addedTheater.getName()).isEqualTo("testTheaterName");
        assertThat(addedTheater.getLocation()).isEqualTo("testTheaterLocation");

        Theater found = theaterService.getTheater(addedTheater.getId());
        logger.info("Theater got " + found);
        assertThat(found.getId()).isEqualTo(addedTheater.getId());
        assertThat(found.getName()).isEqualTo("testTheaterName");
        assertThat(found.getLocation()).isEqualTo("testTheaterLocation");

        theaterService.deleteTheaters(addedTheater.getId());
        logger.info("Theater deleted " + addedTheater);
    }

    @Test
    public void testSearchTheaterByName() {
        Theater newTheater = new Theater();
        newTheater.setName("testTheaterName");
        newTheater.setLocation("testTheaterLocation");

        Theater addedTheater = theaterService.addTheater(newTheater);
        logger.info("Theater added " + addedTheater);

        Iterable<Theater> found = theaterService.getTheatersByName("testTheaterName");
        logger.info("Theaters got " + found);
        found.forEach(theater -> assertThat(theater.getName()).isEqualTo("testTheaterName"));
    }
}
