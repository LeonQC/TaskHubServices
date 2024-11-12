package com.junlinc.rest.controller;

import com.junlinc.rest.domain.Comment;
import com.junlinc.rest.domain.Task;
import com.junlinc.rest.domain.TaskHistory;
import com.junlinc.rest.domain.TaskLink;
import com.junlinc.rest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/API/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //任务标题
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(Map.of(
                "taskId", createdTask.getId(),
                "message", "Task created successfully",
                "task", createdTask
        ));
    }
    @GetMapping("/{id}/title")
    public ResponseEntity<Map<String, String>> getTaskTitle(@PathVariable String id) {
        String title = taskService.getTaskTitleById(id);
        return ResponseEntity.ok(Map.of("title", title));
    }
    @PutMapping("/{id}/title")
    public ResponseEntity<Map<String, Object>> updateTaskTitle(@PathVariable String id, @RequestBody Map<String, String> request) {
        String newTitle = request.get("title");
        Task updatedTask = taskService.updateTaskTitleById(id, newTitle);
        return ResponseEntity.ok(Map.of(
                "taskId", updatedTask.getId(),
                "message", "Task title updated successfully",
                "title", updatedTask.getTitle()
        ));
    }

    //任务描述
    @GetMapping("/{id}/description")
    public ResponseEntity<Map<String, String>> getTaskDescription(@PathVariable String id) {
        String description = taskService.getTaskDescriptionById(id);
        return ResponseEntity.ok(Map.of("description", description));
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<Map<String, Object>> updateTaskDescription(@PathVariable String id, @RequestBody Map<String, String> request) {
        String newDescription = request.get("description");
        Task updatedTask = taskService.updateTaskDescriptionById(id, newDescription);
        return ResponseEntity.ok(Map.of(
                "taskId", updatedTask.getId(),
                "message", "Task description updated successfully",
                "description", updatedTask.getDescription()
        ));
    }

    // attach
    @PostMapping("/{id}/attachments")
    public ResponseEntity<Map<String, Object>> addAttachment(@PathVariable String id, @RequestBody Map<String, String> request) {
        String attachmentUrl = request.get("attachmentUrl");
        Task updatedTask = taskService.addAttachment(id, attachmentUrl);
        return ResponseEntity.ok(Map.of(
                "taskId", updatedTask.getId(),
                "message", "Attachment added successfully"
        ));
    }

    @GetMapping("/{id}/attachments")
    public ResponseEntity<List<String>> getAttachments(@PathVariable String id) {
        List<String> attachments = taskService.getAttachments(id);
        return ResponseEntity.ok(attachments);
    }

    @DeleteMapping("/{id}/attachments")
    public ResponseEntity<Map<String, Object>> deleteAttachment(@PathVariable String id, @RequestParam String attachmentId) {
        Task updatedTask = taskService.deleteAttachment(id, attachmentId);
        return ResponseEntity.ok(Map.of(
                "taskId", updatedTask.getId(),
                "message", "Attachment deleted successfully"
        ));
    }


    @PostMapping("/{id}/childissue")
    public ResponseEntity<Map<String, Object>> addChildIssue(@PathVariable String id, @RequestBody Task childTask) {
        Task updatedTask = taskService.addChildIssue(id, childTask);
        return ResponseEntity.ok(Map.of(
                "childTaskId", childTask.getId(),
                "message", "Child issue added successfully"
        ));
    }


    @GetMapping("/{id}/childissue")
    public ResponseEntity<List<Task>> getChildIssues(@PathVariable String id) {
        List<Task> childIssues = taskService.getChildIssues(id);
        return ResponseEntity.ok(childIssues);
    }


    @PostMapping("/{id}/links")
    public ResponseEntity<Map<String, Object>> addLink(@PathVariable String id, @RequestBody TaskLink link) {
        Task updatedTask = taskService.addLink(id, link);
        return ResponseEntity.ok(Map.of(
                "linkId", link.getLinkedTaskId(),
                "message", "Issue linked successfully"
        ));
    }
    //@return api
    @GetMapping("/{id}/links")
    public ResponseEntity<List<TaskLink>> getLinks(@PathVariable String id) {
        List<TaskLink> links = taskService.getLinks(id);
        return ResponseEntity.ok(links);
    }


    @PostMapping("/{id}/comments")
    public ResponseEntity<Map<String, Object>> addComment(@PathVariable String id, @RequestBody Comment comment) {
        Task updatedTask = taskService.addComment(id, comment);
        return ResponseEntity.ok(Map.of(
                "commentId", comment.getCommentId(),
                "message", "Comment added successfully"
        ));
    }


    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String id) {
        List<Comment> comments = taskService.getComments(id);
        return ResponseEntity.ok(comments);
    }


    @DeleteMapping("/{id}/comments/{commentId}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable String id, @PathVariable String commentId) {
        Task updatedTask = taskService.deleteComment(id, commentId);
        return ResponseEntity.ok(Map.of(
                "taskId", updatedTask.getId(),
                "message", "Comment deleted successfully"
        ));
    }


    @GetMapping("/{id}/history")
    public ResponseEntity<List<TaskHistory>> getHistory(@PathVariable String id) {
        List<TaskHistory> history = taskService.getHistory(id);
        return ResponseEntity.ok(history);
    }


    @GetMapping("/{id}/details")
    public ResponseEntity<Task> getTaskDetails(@PathVariable String id) {
        Task task = taskService.getTaskDetailsById(id);
        return ResponseEntity.ok(task);
    }
    @PutMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> updateTaskDetails(@PathVariable String id, @RequestBody Task updatedTask) {
        Task savedTask = taskService.updateTaskDetails(id, updatedTask);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "msg", "Task updated successfully",
                "task", savedTask
        ));
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateTaskStatus(@PathVariable String id, @RequestBody Map<String, String> request) {
        String newStatus = request.get("status");
        Task updatedTask = taskService.updateTaskStatus(id, newStatus);
        return ResponseEntity.ok(Map.of(
                "taskId", updatedTask.getId(),
                "message", "Task status updated successfully"
        ));
    }
}
