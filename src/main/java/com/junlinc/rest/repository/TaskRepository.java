package com.junlinc.rest.repository;

import com.junlinc.rest.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
