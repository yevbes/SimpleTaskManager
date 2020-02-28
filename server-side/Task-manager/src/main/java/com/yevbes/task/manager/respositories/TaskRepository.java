package com.yevbes.task.manager.respositories;

import com.yevbes.task.manager.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByCreatedTrue();

    List<Task> findByDoingTrue();

    List<Task> findByFinishedTrue();
}
