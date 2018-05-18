package com.wenqi.users.entity.impl;
import com.wenqi.users.entity.User;


public class UserImpl implements User{
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String pin;

    public UserImpl(){

    }

    public UserImpl(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
