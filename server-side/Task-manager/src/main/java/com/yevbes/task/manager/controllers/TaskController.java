package com.yevbes.task.manager.controllers;

import com.yevbes.task.manager.exceptions.TaskListIsEmptyException;
import com.yevbes.task.manager.exceptions.TaskNotFoundException;
import com.yevbes.task.manager.exceptions.TaskUpdateException;
import com.yevbes.task.manager.models.Task;
import com.yevbes.task.manager.services.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Redirect root to swagger documentation
     * @param httpServletResponse http servlet response
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void redirectRootToSwagger(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "swagger-ui.html");
        httpServletResponse.setStatus(302);
    }

    @ApiOperation(value = "Create task", notes = "Return a task values" )
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String create(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam int color
    ){
        Task task = taskService.create(title, description, color);
        return task.toString();
    }

    @ApiOperation(value = "Get task by ID", notes = "Return a task" )
    @RequestMapping(method = RequestMethod.GET ,value = "/get")
    public Task getTask(@RequestParam String id) {
        Task task = taskService.getById(id);
        if (task == null)
            throw new TaskNotFoundException(id);
        return task;
    }

    @ApiOperation(value = "Get all tasks", notes = "Return all tasks" )
    @RequestMapping(method = RequestMethod.GET ,value = "/getAll")
    public List<Task> getAll() {
        List<Task> tasks = taskService.getAll();
        if (tasks.isEmpty())
            throw new TaskListIsEmptyException("tasks");
        return tasks;
    }

    @ApiOperation(value = "Get all created tasks", notes = "Return all created tasks" )
    @RequestMapping(method = RequestMethod.GET ,value = "/getCreatedTasks")
    public List<Task> getCreatedTasks() {
        List<Task> tasks = taskService.getCreatedTasks();
        if (tasks.isEmpty())
            throw new TaskListIsEmptyException("created tasks");
        return tasks;
    }

    @ApiOperation(value = "Get all doing tasks", notes = "Return all doing tasks" )
    @RequestMapping(method = RequestMethod.GET ,value = "/getDoingTasks")
    public List<Task> getDoingTasks() {
        List<Task> tasks = taskService.getDoingTasks();
        if (tasks.isEmpty())
            throw new TaskListIsEmptyException("doing tasks");
        return tasks;
    }

    @ApiOperation(value = "Get all finished tasks", notes = "Return all finished tasks" )
    @RequestMapping(method = RequestMethod.GET ,value = "/getFinishedTasks")
    public List<Task> getFinishedTasks() {
        List<Task> tasks = taskService.getFinishedTasks();
        if (tasks.isEmpty())
            throw new TaskListIsEmptyException("finished tasks");
        return tasks;
    }

    @ApiOperation(value = "Update task", notes = "Return values of updated task" )
    @RequestMapping(method = RequestMethod.PUT ,value = "/update")
    public String update(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam int color,
                         @RequestParam String id){
        Task task = taskService.update(title, description, color, id);
        if (task == null)
            throw new TaskUpdateException(id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to created", notes = "Return values of updated task" )
    @RequestMapping(method = RequestMethod.PUT ,value = "/updateToCreated")
    public String updateToCreated(@RequestParam String id){
        Task task = taskService.updateToCreated(id);
        if (task == null)
            throw new TaskUpdateException(id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to doing", notes = "Return values of updated task" )
    @RequestMapping(method = RequestMethod.PUT ,value = "/updateToDoing")
    public String updateToDoing(@RequestParam String id){
        Task task = taskService.updateToDoing(id);
        if (task == null)
            throw new TaskUpdateException(id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to finished", notes = "Return values of updated task" )
    @RequestMapping(method = RequestMethod.PUT ,value = "/updateToFinished")
    public String updateToFinished(@RequestParam String id){
        Task task = taskService.updateToFinished(id);
        if (task == null)
            throw new TaskUpdateException(id);
        return task.toString();
    }

    @ApiOperation(value = "Delete task by ID", notes = "Delete task" )
    @RequestMapping(method = RequestMethod.DELETE ,value = "/delete")
    public String delete(@RequestParam String id){
        Task task = taskService.delete(id);
        if (task == null)
            throw new TaskUpdateException(id);
        return "Task deleted";
    }

    @ApiOperation(value = "Delete all tasks", notes = "Delete tasks" )
    @RequestMapping(method = RequestMethod.DELETE ,value = "/deleteAll")
    public String deleteAll(){
        taskService.deleteAll();
        return "All tasks was deleted";
    }
}
