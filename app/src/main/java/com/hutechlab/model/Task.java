package com.hutechlab.model;

public class Task {
    private String taskName;
    private String taskInfo;
    private String taskEmp;
    private String taskDate;
    private String taskTime;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Task() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public String getTaskEmp() {
        return taskEmp;
    }

    public void setTaskEmp(String taskEmp) {
        this.taskEmp = taskEmp;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public Task(String taskName, String taskInfo, String taskEmp, String taskDate, String taskTime) {
        this.taskName = taskName;
        this.taskInfo = taskInfo;
        this.taskEmp = taskEmp;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
    }
}
