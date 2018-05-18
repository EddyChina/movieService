package com.wenqi.theaters.service;

import com.wenqi.theaters.entity.Theater;
import com.wenqi.theaters.entity.impl.TheaterImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheaterServiceTest {
    @Autowired
    private TheaterService theaterService;

    @Test
    public void testAddTheater() {
        Theater theaterToCreate = new TheaterImpl(0);
        assertThat(theaterService.addTheater(theaterToCreate)).isEqualTo(theaterToCreate);
    }

    @Test
    public void testGetTheater() {
        Theater theaterGet = theaterService.getTheater(0);

        assertThat(theaterGet instanceof Theater).isTrue();
        assertThat(theaterGet.getId()).isEqualTo(0);
        assertThat(theaterGet.getName()).isNull();
        assertThat(theaterGet.getLocation()).isNull();
    }
}
