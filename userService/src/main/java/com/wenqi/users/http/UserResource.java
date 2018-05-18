package com.wenqi.users.http;


import com.wenqi.users.entity.User;
import com.wenqi.users.entity.impl.UserImpl;
import com.wenqi.users.http.entity.HttpUser;
import com.wenqi.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpUser> createUser(@RequestBody HttpUser newUser) {
        User userToCreate = convert(newUser);
        logger.info("Create user: " + userToCreate);
        User addedUser = userService.addUser(userToCreate);
        return new ResponseEntity<>(new HttpUser(addedUser), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<HttpUser> getUserById(@PathVariable("userId") long userId) {
        logger.info("getting user by id: " + userId);
        User user = userService.getUser(userId);
        return new ResponseEntity<>(new HttpUser(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<HttpUser>> getUserSearch(@RequestParam(value = "firstName", required = false) String firstName,
                                                        @RequestParam(value = "lastName", required = false) String lastName) {
        logger.info("user search firstName= " + firstName + " lastName= " + lastName);
        Iterable<User> found = userService.getUsers(firstName, lastName);
        List<HttpUser> returnList = new ArrayList<>();
        for (User user : found) {
            returnList.add(new HttpUser(user));
        }
        return new ResponseEntity<>(returnList, HttpStatus.OK);
    }

    /**
     * Not pushing this into business layer. Could be a util as well
     *
     * @param httpUser
     * @return
     */
    private User convert(HttpUser httpUser) {
        UserImpl user = new UserImpl();
        user.setUserName(httpUser.userName);
        user.setFirstName(httpUser.firstName);
        user.setLastName(httpUser.lastName);
        user.setPin(httpUser.pin);
        return user;
    }
}
