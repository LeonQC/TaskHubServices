package com.junlinc.rest.service;

import com.junlinc.rest.domain.Project;
import com.junlinc.rest.domain.Task;
import com.junlinc.rest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Create a new project
    public Project createProject(Project project) {
        project.setId(UUID.randomUUID().toString());
        return projectRepository.save(project);
    }
    //create task in column
    public Task createTaskInColumn(String projectId, Task task, String column) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getColumns().contains(column)) {
            throw new RuntimeException("Invalid column name: " + column);
        }

        task.setId(UUID.randomUUID().toString());
        task.setColumn(column);
        project.getTasks().add(task);
        projectRepository.save(project);
        return task;
    }


    // Move task to another column
    public Task moveTask(String projectId, String taskId, String newColumn) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = project.getTasks().stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setColumn(newColumn);
        projectRepository.save(project); // Save updated project
        return task;
    }

    public Project inviteUserToProject(String userId, String projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        if (!project.getInvitedUsers().contains(userId)) {
            project.getInvitedUsers().add(userId);
            projectRepository.save(project);
        }
        return project;
    }

    // Assign a user to a task in a project
    public Task assignUserToTask(String userId, String projectId, String taskId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = project.getTasks().stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getOwners().contains(userId)) {
            task.getOwners().add(userId);
            projectRepository.save(project);
        }

        return task;
    }
}