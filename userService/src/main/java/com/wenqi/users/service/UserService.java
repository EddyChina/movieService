package com.wenqi.users.service;

import com.wenqi.users.entity.User;

public interface UserService {
    User addUser(User user);

    void updateUser(User user);

    User getUser(long userId);

    /**
     * Search user by first or last name, partial searches also performed
     *
     * @param firstName
     * @param lastName
     * @return Empty list is returned if none found
     */
    Iterable<User> getUsers(String firstName, String lastName);

    boolean isPinValid(long userId, String pin);

    void deleteUser(User user);

}
