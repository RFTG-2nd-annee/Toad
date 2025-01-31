package com.toad.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer actor_id;
    private String first_name;
    private String last_name;
    private Timestamp last_update;

    // Getters and Setters
    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }
}
