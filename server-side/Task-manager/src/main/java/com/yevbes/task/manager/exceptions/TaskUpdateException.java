package com.yevbes.task.manager.exceptions;

public class TaskUpdateException extends RuntimeException {

    public TaskUpdateException(String id) {
        super("Task with id "+id+" can not be updated it not exist!");
    }
}
