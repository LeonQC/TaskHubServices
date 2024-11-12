package com.junlinc.rest.repository;
import com.junlinc.rest.domain.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
