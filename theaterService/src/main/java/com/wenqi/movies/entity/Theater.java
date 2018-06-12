package com.wenqi.movies.entity;

import javax.persistence.*;

@Entity
@Table(name = "theaters")
public class Theater {
    @Id
    @Column(name = "theater_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    public Theater() {

    }

    public Theater(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return super.toString();
    }
}
