package com.yevbes.task.manager.services;

import com.yevbes.task.manager.models.Task;
import com.yevbes.task.manager.respositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Create Task
     *
     * @param title       Task title
     * @param description Task description
     * @param color       Task color
     * @return Task
     */
    public Task create(
            String title,
            String description,
            int color) {
        return taskRepository.save(new Task(title, description, color));
    }

    /**
     * Get all tasks
     *
     * @return Task list
     */
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * Get task by id
     *
     * @param id Task id
     * @return Task if exists
     */
    public Task getById(String id) {
        Task task = null;
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent())
            task = optionalTask.get();
        return task;
    }


    /**
     * Filter list by created tasks
     *
     * @return Task list
     */
    public List<Task> getCreatedTasks() {
        return taskRepository.findByCreatedTrue();
    }

    /**
     * Filter list by doing tasks
     *
     * @return Task list
     */
    public List<Task> getDoingTasks() {
        return taskRepository.findByDoingTrue();
    }

    /**
     * Filter list by finished tasks
     *
     * @return Task list
     */
    public List<Task> getFinishedTasks() {
        return taskRepository.findByFinishedTrue();
    }

    /**
     * Update task
     *
     * @param title       Task title
     * @param description Task description
     * @param color       Task color
     * @return Task
     */
    public Task update
    (String title,
     String description,
     int color,
     String id) {
        Task task = null;
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
            task.setTitle(title);
            task.setDescription(description);
            task.setColor(color);
        }
        if (task != null)
            return taskRepository.save(task);
        return null;
    }

    /**
     * Update task to created
     *
     * @param id Task
     * @return Task
     */
    public Task updateToCreated(String id) {
        Task task = null;
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
            task.setTaskToCreated();
        }
        if (task != null)
            return taskRepository.save(task);
        return null;
    }

    /**
     * Update task to doing
     *
     * @param id Task
     * @return Task
     */
    public Task updateToDoing(String id) {
        Task task = null;
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
            task.setTaskToDoing();
        }
        if (task != null)
            return taskRepository.save(task);
        return null;
    }

    /**
     * Update task to finished
     *
     * @param id Task
     * @return Task
     */
    public Task updateToFinished(String id) {
        Task task = null;
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
            task.setTaskToFinished();
        }
        if (task != null)
            return taskRepository.save(task);
        return null;
    }

    /**
     * Delete all tasks
     */
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    /**
     * Delete task by id
     * @param id Task
     * @return Deleted task or null
     */
    public Task delete(String id) {
        Task task = null;
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            task = optionalTask.get();
        }
        if (task != null)
            taskRepository.delete(task);
        return task;
    }

}
