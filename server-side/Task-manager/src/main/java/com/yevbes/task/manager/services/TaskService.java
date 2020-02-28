package com.yevbes.task.manager.services;

import com.yevbes.task.manager.models.Task;

import java.util.List;

public interface TaskService {
    Task create(
            String title,
            String description,
            int color
    );

    List<Task> getAll();

    Task getById(String id);

    List<Task> getCreatedTasks();

    List<Task> getDoingTasks();

    List<Task> getFinishedTasks();

    Task update(
            String title,
            String description,
            int color,
            String id
    );

    Task updateToCreated(String id);

    Task updateToDoing(String id);

    Task updateToFinished(String id);

    void deleteAll();

    Task delete(String id);
}
