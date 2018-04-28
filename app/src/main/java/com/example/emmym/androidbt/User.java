package com.example.emmym.androidbt;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User {
    String name;
    long timestamp = 0;

    public User() {}

    public User(String name) {
        this.name = name;
        timestamp = 0;
    }

    public User(String name, long timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getName() {
        if(this.name == null) return null;
        return this.name;
    }

    public long getTimestamp() {
        if(this.timestamp == 0) return 0;
        return this.timestamp;
    }

    public String toString() {
        return name + ": " + timestamp;
    }

    public boolean hasSameName(User anotherUser) {
        if(this.name.equals(anotherUser.getName())) {
            return true;
        }
        return false;
    }

    public boolean hasSameNameAndTime(User anotherUser) {
        if(this.name.equals(anotherUser.getName())
                && this.timestamp == anotherUser.getTimestamp()) {
            return true;
        }
        return false;
    }

    public boolean hasSameNameAndTime(ArrayList<User> anotherUsers) {
        for(User user: anotherUsers) {
            if(this.hasSameNameAndTime(user)) return true;
        }
        return false;
    }

    public String isDetected(User oldUser) {
        if(this.getName() == null || oldUser.getName() == null || this.getTimestamp() == 0 || oldUser.getTimestamp() == 0) return null;
        if(this.getName().equals(oldUser.getName())
                && this.getTimestamp() != oldUser.getTimestamp()) {
            return this.getName();
        }
        return null;

    }
}