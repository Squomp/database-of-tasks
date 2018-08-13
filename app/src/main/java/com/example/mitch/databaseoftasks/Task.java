package com.example.mitch.databaseoftasks;

import java.io.Serializable;

public class Task implements Serializable{
    private String TaskDesc;
    private double timeSpent;
    private boolean isCompleted;

    public Task(String taskDesc) {
        TaskDesc = taskDesc;
        this.timeSpent = 0;
        this.isCompleted = false;
    }

    public String getTaskDesc() {
        return TaskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        TaskDesc = taskDesc;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
