package com.example.mitch.databaseoftasks;

import java.io.Serializable;

public class Task implements Serializable{

    private String strID = "";
    private String TaskDesc;
    private double timeSpent;
    private boolean isCompleted;

    public Task() {
    }

    public Task(String taskDesc) {
        TaskDesc = taskDesc;
        this.timeSpent = 0;
        this.isCompleted = false;
    }

    public Task(String strID, String taskDesc, double timeSpent, boolean isCompleted) {
        this.strID = strID;
        TaskDesc = taskDesc;
        this.timeSpent = timeSpent;
        this.isCompleted = isCompleted;
    }

    public String getStrID() {
        return strID;
    }

    public void setStrID(String strID) {
        this.strID = strID;
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
