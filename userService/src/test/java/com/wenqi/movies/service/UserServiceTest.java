package com.wenqi.movies.service;

import com.wenqi.movies.entity.User;
import com.wenqi.movies.entity.impl.UserImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUser() {
        User userGet = userService.getUser(0);

        assertThat(userGet instanceof User).isTrue();
        assertThat(userGet.getId()).isEqualTo(0);
        assertThat(userGet.getUserName()).isNull();
        assertThat(userGet.getFirstName()).isNull();
        assertThat(userGet.getLastName()).isNull();
        assertThat(userGet.getPin()).isNull();
    }

    @Test
    public void testAddUser() {
        User userToCreate = new UserImpl(0);
        assertThat(userService.addUser(userToCreate)).isEqualTo(userToCreate);
    }

}
