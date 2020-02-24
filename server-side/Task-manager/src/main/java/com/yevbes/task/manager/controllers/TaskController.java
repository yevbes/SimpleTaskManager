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
    @RequestMapping("/create")
    public String create(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam int color
    ){
        Task task = taskService.create(title, description, color);
        return task.toString();
    }

    @ApiOperation(value = "Get task by ID", notes = "Return a task" )
    @RequestMapping("/get")
    public Task getTask(@RequestParam String id) {
        return taskService.getById(id);
    }

    @ApiOperation(value = "Get all tasks", notes = "Return all tasks" )
    @RequestMapping("/getAll")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @ApiOperation(value = "Get all created tasks", notes = "Return all created tasks" )
    @RequestMapping("/getCreatedTasks")
    public List<Task> getCreatedTasks() {
        return taskService.getCreatedTasks();
    }

    @ApiOperation(value = "Get all doing tasks", notes = "Return all doing tasks" )
    @RequestMapping("/getDoingTasks")
    public List<Task> getDoingTasks() {
        return taskService.getDoingTasks();
    }

    @ApiOperation(value = "Get all finished tasks", notes = "Return all finished tasks" )
    @RequestMapping("/getFinishedTasks")
    public List<Task> getFinishedTasks() {
        return taskService.getFinishedTasks();
    }

    @ApiOperation(value = "Update task", notes = "Return values of updated task" )
    @RequestMapping("/update")
    public String update(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam int color,
                         @RequestParam String id){
        Task task = taskService.update(title, description, color, id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to created", notes = "Return values of updated task" )
    @RequestMapping("/updateToCreated")
    public String updateToCreated(@RequestParam String id){
        Task task = taskService.updateToCreated(id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to doing", notes = "Return values of updated task" )
    @RequestMapping("/updateToDoing")
    public String updateToDoing(@RequestParam String id){
        Task task = taskService.updateToDoing(id);
        return task.toString();
    }

    @ApiOperation(value = "Update task to finished", notes = "Return values of updated task" )
    @RequestMapping("/updateToFinished")
    public String updateToFinished(@RequestParam String id){
        Task task = taskService.updateToFinished(id);
        return task.toString();
    }

    @ApiOperation(value = "Delete task by ID", notes = "Delete task" )
    @RequestMapping("/delete")
    public String delete(@RequestParam String id){
        taskService.delete(id);
        return "Task deleted";
    }

    @ApiOperation(value = "Delete all tasks", notes = "Delete tasks" )
    @RequestMapping("/deleteAll")
    public String deleteAll(){
        taskService.deleteAll();
        return "All tasks was deleted";
    }
}
