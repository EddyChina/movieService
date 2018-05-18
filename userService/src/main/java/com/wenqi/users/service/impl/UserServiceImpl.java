package com.wenqi.users.service.impl;

import com.wenqi.users.entity.User;
import com.wenqi.users.entity.impl.UserImpl;
import com.wenqi.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User addUser(User user) {
        return user;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUser(long userId) {
        return new UserImpl(userId);
    }

    @Override
    public boolean isPinValid(long userId, String pin) {
        return false;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public Iterable<User> getUsers(String firstName, String lastName) {
        List<User> list = new ArrayList<>();
        list.add(new UserImpl(1));
        list.add(new UserImpl(2));
        return list;
    }
}
