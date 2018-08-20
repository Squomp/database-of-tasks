package com.example.mitch.databaseoftasks;

import java.util.List;

public interface DBConnection {
    void addTask(Task task);
    Task getTask(String id);
    List<Task> getAllTasks();
    int updateTask(Task task);
    void deleteTask(Task task);
}
