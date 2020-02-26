package com.yevbes.task.manager.exceptions;

public class TaskListIsEmptyException extends RuntimeException {
    public TaskListIsEmptyException(String collection) {
        super("There is not " + collection + "!");
    }
}
