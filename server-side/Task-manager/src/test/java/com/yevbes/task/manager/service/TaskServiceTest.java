package com.yevbes.task.manager.service;

import com.yevbes.task.manager.models.Task;
import com.yevbes.task.manager.respositories.TaskRepository;
import com.yevbes.task.manager.services.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    private Task task1;
    private Task task2;
    private Task task3;
    private final List<Task> tasks = new ArrayList<>();
    private final List<Task> createdTasks = new ArrayList<>();
    private final List<Task> doingTasks = new ArrayList<>();
    private final List<Task> finishedTasks = new ArrayList<>();

    private final String task1Title = "Task 1";
    private final String task2Title = "Task 2";
    private final String task3Title = "Task 3";
    private final String task1Description = "Task 1 Description";
    private final String task2Description = "Task 2 Description";
    private final String task3Description = "Task 3 Description";
    private final int task1Color = 111;
    private final int task2Color = 222;
    private final int task3Color = 333;
    private String idTask1;

    @Before
    public void setUp() {
        task1 = new Task(task1Title, task1Description, task1Color);
        task2 = new Task(task2Title, task2Description, task2Color);
        task3 = new Task(task3Title, task3Description, task3Color);

        idTask1 = task1.getId();

        task1.setTaskToCreated();
        task2.setTaskToDoing();
        task3.setTaskToFinished();

        createdTasks.add(task1);
        doingTasks.add(task2);
        finishedTasks.add(task3);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        Mockito.when(taskRepository.findByCreatedTrue())
                .thenReturn(createdTasks);

        Mockito.when(taskRepository.findByDoingTrue())
                .thenReturn(doingTasks);

        Mockito.when(taskRepository.findByFinishedTrue())
                .thenReturn(finishedTasks);

        Mockito.when(taskRepository.save(task1)).thenReturn(task1);
        Mockito.when(taskRepository.findById(idTask1)).thenReturn(java.util.Optional.ofNullable(task1));
    }

    @Test
    public void testFindAll_thenTaskListShouldBeReturned() {
        List<Task> foundTasks = taskService.getAll();

        assertNotNull(foundTasks);
        assertEquals(3, foundTasks.size());
    }

    @Test
    public void testFindByCreatedTrue_thenTaskListCreatedShouldBeReturned() {
        List<Task> foundCreatedTasks = taskService.getCreatedTasks();

        assertNotNull(foundCreatedTasks);
        assertEquals(1, foundCreatedTasks.size());
    }

    @Test
    public void testFindByDoingTrue_thenTaskListDoingShouldBeReturned() {
        List<Task> foundDoingTasks = taskService.getDoingTasks();

        assertNotNull(foundDoingTasks);
        assertEquals(1, foundDoingTasks.size());
    }

    @Test
    public void testFindByFinishedTrue_thenTaskListFinishedShouldBeReturned() {
        List<Task> foundFinishedTasks = taskService.getFinishedTasks();

        assertNotNull(foundFinishedTasks);
        assertEquals(1, foundFinishedTasks.size());
    }

    @Test
    public void testFindByID_thenTask1ShouldBeReturned() {
        Task found = taskService.getById(idTask1);

        assertNotNull(found);
        assertEquals(task1.getTitle(), found.getTitle());
        assertEquals(task1.getDescription(), found.getDescription());
        assertEquals(task1.getColor(), found.getColor());
        assertEquals(task1.getId(), found.getId());
    }

    @Test
    public void testUpdateTask_thenTaskShouldBeReturned() {
        Task found = taskService.update(
                "Task 1 title updated",
                "Task 1 description updated",
                111111,
                idTask1
        );

        assertNotNull(found);
        assertEquals(task1.getId(), found.getId());
    }

    @Test
    public void testUpdateTaskToCreated_thenTaskShouldBeReturned() {
        Task found = taskService.updateToCreated(idTask1);

        assertNotNull(found);
        assertEquals(task1.getId(), found.getId());
    }

    @Test
    public void testUpdateTaskToDoing_thenTaskShouldBeReturned() {
        Task found = taskService.updateToDoing(idTask1);

        assertNotNull(found);
        assertEquals(task1.getId(), found.getId());
    }

    @Test
    public void testUpdateTaskToFinished_thenTaskShouldBeReturned() {
        Task found = taskService.updateToFinished(idTask1);

        assertNotNull(found);
        assertEquals(task1.getId(), found.getId());
    }

    @Test
    public void testDeleteTaskById() {
        taskService.delete(task1.getId());

        Mockito.verify(taskRepository, Mockito.times(1))
                .findById(task1.getId());
    }

    @Test
    public void testDeleteAll() {
        taskService.deleteAll();

        Mockito.verify(taskRepository, Mockito.times(1))
                .deleteAll();
    }
}
