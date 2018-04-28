package com.example.emmym.androidbt;

import com.google.firebase.database.IgnoreExtraProperties;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Machine {
    private static final String TAG = "MainActivity";
    String name;
    Map<String, User> users = new HashMap<>();

    public Machine() {
    }

    public Map<String, User> getUsers() {
        if(this.users == null) return null;
        return users;
    }

    public int userCount() {
        return users.size();
    }

    public String getName() {
        return this.name;
    }

    public void printUserInfo() {
        Collection<User> users = this.users.values();
        for(User user: users) {
            Log.d(TAG, user.getName() + ": " + user.getTimestamp());
        }
    }

    //TODO: brute force right now, someone change this so I can do it in linear time
    public ArrayList<String> areUsersDetected(Machine oldMachine) {
        if(this.getUsers() == null || oldMachine.getUsers() == null) return null;
        ArrayList<String> result = new ArrayList<>();
        Collection<User> users = this.getUsers().values();
        Collection<User> oldUsers = oldMachine.getUsers().values();
        for(User user: users) {
            for(User oldUser: oldUsers) {
                String userName = user.isDetected(oldUser);
                if(userName != null)
                    result.add(userName);
            }
        }
        if(result.size() == 0) return null;
        return result;
    }
//
//    /**
//     * Temporary stuff
//     * @param anotherMachine
//     * @return
//     */
//    public ArrayList<User> userChanged(Machine anotherMachine) {
//        ArrayList<User> result = new ArrayList<User>();
//        for(User user: this.users) {
//            if(!anotherMachine.getUsers().contains(user)) {
//                result.add(user);
//            }
//        }
//        return result;
//    }
}