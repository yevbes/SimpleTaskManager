package com.yevbes.task.manager.exceptions;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String id){
        super("Task is not found : " + id+"!");
    }
}
