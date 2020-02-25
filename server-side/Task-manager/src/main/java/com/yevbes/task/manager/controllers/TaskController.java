package com.yevbes.task.manager.controllers;

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
        return taskService.getById(id);
    }

    @ApiOperation(value = "Get all tasks", notes = "Return all tasks" )
    @RequestMapping(method = RequestMethod.GET ,value = "/getAll")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @ApiOperation(value = "Get all created tasks", notes = "Return all created tasks" )
    @RequestMapping(method = RequestMethod.GET ,value = "/getCreatedTasks")
    public List<Task> getCreatedTasks() {
        return taskService.getCreatedTasks();
    }

    @ApiOperation(value = "Get all doing tasks", notes = "Return all doing tasks" )
    @RequestMapping(method = RequestMethod.GET ,value = "/getDoingTasks")
    public List<Task> getDoingTasks() {
        return taskService.getDoingTasks();
    }

    @ApiOperation(value = "Get all finished tasks", notes = "Return all finished tasks" )
    @RequestMapping(method = RequestMethod.GET ,value = "/getFinishedTasks")
    public List<Task> getFinishedTasks() {
        return taskService.getFinishedTasks();
    }

    @ApiOperation(value = "Update task", notes = "Return values of updated task" )
    @RequestMapping(method = RequestMethod.PUT ,value = "/update")
    public String update(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam int color,
                         @RequestParam String id){
        Task task = taskService.update(title, description, color, id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to created", notes = "Return values of updated task" )
    @RequestMapping(method = RequestMethod.PUT ,value = "/updateToCreated")
    public String updateToCreated(@RequestParam String id){
        Task task = taskService.updateToCreated(id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to doing", notes = "Return values of updated task" )
    @RequestMapping(method = RequestMethod.PUT ,value = "/updateToDoing")
    public String updateToDoing(@RequestParam String id){
        Task task = taskService.updateToDoing(id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to finished", notes = "Return values of updated task" )
    @RequestMapping(method = RequestMethod.PUT ,value = "/updateToFinished")
    public String updateToFinished(@RequestParam String id){
        Task task = taskService.updateToFinished(id);
        return task.toString();
    }

    @ApiOperation(value = "Delete task by ID", notes = "Delete task" )
    @RequestMapping(method = RequestMethod.DELETE ,value = "/delete")
    public String delete(@RequestParam String id){
        taskService.delete(id);
        return "Task deleted";
    }

    @ApiOperation(value = "Delete all tasks", notes = "Delete tasks" )
    @RequestMapping(method = RequestMethod.DELETE ,value = "/deleteAll")
    public String deleteAll(){
        taskService.deleteAll();
        return "All tasks was deleted";
    }
}
