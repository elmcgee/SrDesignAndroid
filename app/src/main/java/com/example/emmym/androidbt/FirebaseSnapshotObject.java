package com.example.emmym.androidbt;

import android.util.Log;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FirebaseSnapshotObject extends Object {
    private static final String TAG = "MainActivity";
    Map<String, Machine> machine = new HashMap<>();

    public FirebaseSnapshotObject () {}
//
//    public FirebaseSnapshotObject() {
//        machines = new ArrayList<>();
//    }
//
//    public FirebaseSnapshotObject(Machine machine) {
//        this.machines = new ArrayList<>();
//        machines.add(machine);
//    }
//
//    public FirebaseSnapshotObject(ArrayList<Machine> machines) {
//        this.machines = new ArrayList<>();
//        this.machines.addAll(machines);
//    }
//

    public Map<String, Machine> getMachines() {
        if(this.machine == null) return null;
        return this.machine;
    }

    public String toString() {
        return machine.toString();
    }

    public void printMachineNames() {
        Collection<Machine> machines = machine.values();
        for(Machine machine: machines) {
            Log.d(TAG, machine.getName());
        }
        // Log.d(TAG, machine.values())
    }

    public void printUserInfo() {
        Collection<Machine> machines = machine.values();
        for(Machine machine: machines) {
            machine.printUserInfo();
        }
    }

    //TODO: potentially be able to recognize multiple people if multiple value changed
    public Map<String, ArrayList<String>> whoWasDetected(FirebaseSnapshotObject oldSnapshot) {
        if(this.getMachines() == null || oldSnapshot.getMachines() == null) return null;
        Map<String, ArrayList<String>> result = new HashMap<>();
        Collection<Machine> machines = this.getMachines().values();
        Collection<Machine> oldMachines = oldSnapshot.getMachines().values();
        for(Machine machine: machines) {
            for(Machine oldMachine: oldMachines) {
                if(machine.getName().equals(oldMachine.getName())) {
                    ArrayList<String> userNames = machine.areUsersDetected(oldMachine);
                    if (userNames != null) {
                        result.put(machine.getName(), userNames);
                    }
                }
            }
        }
        if(result.size() == 0) return null;
        return result;
    }
}