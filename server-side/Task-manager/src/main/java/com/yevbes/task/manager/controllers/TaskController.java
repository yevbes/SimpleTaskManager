package com.yevbes.task.manager.controllers;

import com.yevbes.task.manager.exceptions.TaskDeleteException;
import com.yevbes.task.manager.exceptions.TaskListIsEmptyException;
import com.yevbes.task.manager.exceptions.TaskNotFoundException;
import com.yevbes.task.manager.exceptions.TaskUpdateException;
import com.yevbes.task.manager.models.Task;
import com.yevbes.task.manager.services.impl.TaskServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    /**
     * Redirect root to swagger documentation
     *
     * @param httpServletResponse http servlet response
     */
    @ApiIgnore
    @GetMapping(value = "/")
    public void redirectRootToSwagger(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "swagger-ui.html");
        httpServletResponse.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
    }

    @ApiOperation(value = "Create task", notes = "Return a task values")
    @PostMapping(value = "/create")
    public String create(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam int color,
            HttpServletResponse httpServletResponse
    ) {
        httpServletResponse.setStatus(HttpStatus.CREATED.value()); // Status created
        Task task = taskServiceImpl.create(title, description, color);
        return task.toString();
    }

    @ApiOperation(value = "Get task by ID", notes = "Return a task")
    @GetMapping(value = "/get")
    public Task getTask(
            @RequestParam String id,
            HttpServletResponse httpServletResponse
    ) {
        Task task = taskServiceImpl.getById(id);
        if (task == null)
            throw new TaskNotFoundException(id);

        httpServletResponse.setStatus(HttpStatus.FOUND.value()); // Status found
        return task;
    }

    @ApiOperation(value = "Get all tasks", notes = "Return all tasks")
    @GetMapping(value = "/getAll")
    public List<Task> getAll(HttpServletResponse httpServletResponse) {
        List<Task> tasks = taskServiceImpl.getAll();
        if (tasks.isEmpty())
            throw new TaskListIsEmptyException("tasks");

        httpServletResponse.setStatus(HttpStatus.FOUND.value()); // Status found
        return tasks;
    }

    @ApiOperation(value = "Get all created tasks", notes = "Return all created tasks")
    @GetMapping(value = "/getCreatedTasks")
    public List<Task> getCreatedTasks(HttpServletResponse httpServletResponse) {
        List<Task> tasks = taskServiceImpl.getCreatedTasks();
        if (tasks.isEmpty())
            throw new TaskListIsEmptyException("created tasks");

        httpServletResponse.setStatus(HttpStatus.FOUND.value()); // Status found
        return tasks;
    }

    @ApiOperation(value = "Get all doing tasks", notes = "Return all doing tasks")
    @GetMapping(value = "/getDoingTasks")
    public List<Task> getDoingTasks(HttpServletResponse httpServletResponse) {
        List<Task> tasks = taskServiceImpl.getDoingTasks();
        if (tasks.isEmpty())
            throw new TaskListIsEmptyException("doing tasks");

        httpServletResponse.setStatus(HttpStatus.FOUND.value()); // Status found
        return tasks;
    }

    @ApiOperation(value = "Get all finished tasks", notes = "Return all finished tasks")
    @GetMapping(value = "/getFinishedTasks")
    public List<Task> getFinishedTasks(HttpServletResponse httpServletResponse) {
        List<Task> tasks = taskServiceImpl.getFinishedTasks();
        if (tasks.isEmpty())
            throw new TaskListIsEmptyException("finished tasks");

        httpServletResponse.setStatus(HttpStatus.FOUND.value()); // Status found
        return tasks;
    }

    @ApiOperation(value = "Update task", notes = "Return values of updated task")
    @PutMapping(value = "/update")
    public String update(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam int color,
                         @RequestParam String id,
                         HttpServletResponse httpServletResponse) {
        Task task = taskServiceImpl.update(title, description, color, id);
        if (task == null)
            throw new TaskUpdateException(id);

        httpServletResponse.setStatus(HttpStatus.OK.value()); // Status OK
        return task.toString();
    }

    @ApiOperation(value = "Update task to created", notes = "Return values of updated task")
    @PutMapping(value = "/updateToCreated")
    public String updateToCreated(
            @RequestParam String id,
            HttpServletResponse httpServletResponse
    ) {
        Task task = taskServiceImpl.updateToCreated(id);
        if (task == null)
            throw new TaskUpdateException(id);

        httpServletResponse.setStatus(HttpStatus.OK.value()); // Status OK
        return task.toString();
    }

    @ApiOperation(value = "Update task to doing", notes = "Return values of updated task")
    @PutMapping(value = "/updateToDoing")
    public String updateToDoing(
            @RequestParam String id,
            HttpServletResponse httpServletResponse
    ) {
        Task task = taskServiceImpl.updateToDoing(id);
        if (task == null)
            throw new TaskUpdateException(id);

        httpServletResponse.setStatus(HttpStatus.OK.value()); // Status OK
        return task.toString();
    }

    @ApiOperation(value = "Update task to finished", notes = "Return values of updated task")
    @PutMapping(value = "/updateToFinished")
    public String updateToFinished(
            @RequestParam String id,
            HttpServletResponse httpServletResponse
    ) {
        Task task = taskServiceImpl.updateToFinished(id);
        if (task == null)
            throw new TaskUpdateException(id);

        httpServletResponse.setStatus(HttpStatus.OK.value()); // Status OK
        return task.toString();
    }

    @ApiOperation(value = "Delete task by ID", notes = "Delete task")
    @DeleteMapping(value = "/delete")
    public String delete(
            @RequestParam String id,
            HttpServletResponse httpServletResponse
    ) {
        Task task = taskServiceImpl.delete(id);
        if (task == null)
            throw new TaskDeleteException(id);

        httpServletResponse.setStatus(HttpStatus.OK.value()); // Status OK
        return "Task deleted";
    }

    @ApiOperation(value = "Delete all tasks", notes = "Delete tasks")
    @DeleteMapping(value = "/deleteAll")
    public String deleteAll(HttpServletResponse httpServletResponse) {
        taskServiceImpl.deleteAll();
        httpServletResponse.setStatus(HttpStatus.OK.value()); // Status OK
        return "All tasks was deleted";
    }
}
