package com.example.mitch.databaseoftasks;

import android.nfc.Tag;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseConnection implements DBConnection {

    private DatabaseReference dbTasks;
    private static final String TAG = "FirebaseConnection";
    private List<Task> tasks = new ArrayList<>();

    public FirebaseConnection() {
        dbTasks = FirebaseDatabase.getInstance().getReference("tasks");
    }

    @Override
    public void addTask(Task task) {
        String id = dbTasks.push().getKey();
        task.setStrID(id);
        dbTasks.child(id).setValue(task);
    }

    @Override
    public Task getTask(String id) {
        dbTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Task value = dataSnapshot.getValue(Task.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        dbTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tasks.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Task task = postSnapshot.getValue(Task.class);
                    tasks.add(task);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return tasks;
    }

    @Override
    public int updateTask(Task task) {
        return 0;
    }

    @Override
    public void deleteTask(Task task) {

    }
}
