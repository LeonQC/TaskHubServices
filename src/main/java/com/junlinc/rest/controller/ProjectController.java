package com.junlinc.rest.controller;

import com.junlinc.rest.domain.Project;
import com.junlinc.rest.domain.Task;
import com.junlinc.rest.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/API/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Create a new project
    @PostMapping
    public ResponseEntity<Map<String, Object>> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.ok(Map.of(
                "projectId", createdProject.getId(),
                "message", "Project created successfully"
        ));
    }
    //Create a new task in column
    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Map<String, Object>> createTaskInColumn(
            @PathVariable String projectId,
            @RequestBody Task task,
            @RequestParam String column) {
        Task createdTask = projectService.createTaskInColumn(projectId, task, column);
        return ResponseEntity.ok(Map.of(
                "taskId", createdTask.getId(),
                "column", createdTask.getColumn(),
                "message", "Task created successfully in column " + column
        ));
    }

    // Move task to another column
    @PutMapping("/{projectId}/tasks/{taskId}/move")
    public ResponseEntity<Map<String, Object>> moveTask(
            @PathVariable String projectId,
            @PathVariable String taskId,
            @RequestBody Map<String, String> request) {
        String newColumn = request.get("newColumn");
        Task updatedTask = projectService.moveTask(projectId, taskId, newColumn);
        return ResponseEntity.ok(Map.of(
                "taskId", updatedTask.getId(),
                "newColumn", updatedTask.getColumn(),
                "message", "Task moved successfully"
        ));
    }

    // Invite a user to the project
    @PostMapping("/{projectId}/invite")
    public ResponseEntity<Map<String, Object>> inviteUser(
            @PathVariable String projectId,
            @RequestBody Map<String, String> request) {
        String email = request.get("email");
        Project updatedProject = projectService.inviteUserToProject(projectId, email);
        return ResponseEntity.ok(Map.of(
                "projectId", updatedProject.getId(),
                "email", email,
                "message", "User invited successfully"
        ));
    }
    // Assign a user to a task in a project
    @PostMapping("/Projects/{projectId}/tasks/{taskId}/assignUser")
    public ResponseEntity<Task> assignUserToTask(
            @RequestParam String userId,
            @PathVariable String projectId,
            @PathVariable String taskId) {
        Task task = projectService.assignUserToTask(userId, projectId, taskId);
        return ResponseEntity.ok(task);
    }
}

