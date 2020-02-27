package com.yevbes.task.manager.exceptions;

public class TaskDeleteException extends RuntimeException {
    public TaskDeleteException(String id) {
        super("Task with id " + id + " can not be delete it not exist!");
    }
}
