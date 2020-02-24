package com.yevbes.task.manager.respositories;

import com.yevbes.task.manager.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByCreatedTrue();
    List<Task> findByDoingTrue();
    List<Task> findByFinishedTrue();
}
