package com.junlinc.rest.service;

import com.junlinc.rest.domain.Task;
import com.junlinc.rest.domain.TaskLink;
import com.junlinc.rest.domain.Comment;
import com.junlinc.rest.domain.TaskHistory;
import com.junlinc.rest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    //title
    public String getTaskTitleById(String id) {
        return taskRepository.findById(id)
                .map(Task::getTitle)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }
    public Task updateTaskTitleById(String id, String newTitle) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(newTitle);
        return taskRepository.save(task);
    }

    // description
    public String getTaskDescriptionById(String id) {
        return taskRepository.findById(id)
                .map(Task::getDescription)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTaskDescriptionById(String id, String newDescription) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setDescription(newDescription);
        return taskRepository.save(task);
    }

    // 添加attachment
    public Task addAttachment(String id, String attachmentUrl) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getAttachments() == null) {
            task.setAttachments(List.of(attachmentUrl));
        } else {
            task.getAttachments().add(attachmentUrl);
        }
        return taskRepository.save(task);
    }

    public List<String> getAttachments(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return task.getAttachments();
    }

    // 删除附件
    public Task deleteAttachment(String id, String attachmentId) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getAttachments() != null) {
            task.getAttachments().removeIf(attachment -> attachment.equals(attachmentId));
        }
        return taskRepository.save(task);
    }

    // 添加子任务
    public Task addChildIssue(String id, Task childTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getChildIssues() == null) {
            task.setChildIssues(List.of(childTask));
        } else {
            task.getChildIssues().add(childTask);
        }
        return taskRepository.save(task);
    }

    // 获取子任务
    public List<Task> getChildIssues(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return task.getChildIssues();
    }

    // 添加链接
    public Task addLink(String id, TaskLink link) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getLinks() == null) {
            task.setLinks(List.of(link));
        } else {
            task.getLinks().add(link);
        }
        return taskRepository.save(task);
    }

    // 获取链接
    public List<TaskLink> getLinks(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return task.getLinks();
    }

    // 添加评论
    public Task addComment(String id, Comment comment) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getComments() == null) {
            task.setComments(List.of(comment));
        } else {
            task.getComments().add(comment);
        }
        comment.setCreatedDate(LocalDateTime.now()); // Set comment creation date
        return taskRepository.save(task);
    }

    // 获取评论
    public List<Comment> getComments(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return task.getComments();
    }

    // 删除评论
    public Task deleteComment(String id, String commentId) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getComments() != null) {
            task.getComments().removeIf(comment -> comment.getCommentId().equals(commentId));
        }
        return taskRepository.save(task);
    }

    // 获取历史记录
    public List<TaskHistory> getHistory(String id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        return task.getHistory();
    }

    // 获取任务详细信息
    public Task getTaskDetailsById(String id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    // 更新任务详细信息
    public Task updateTaskDetails(String id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setAssignee(updatedTask.getAssignee());
        existingTask.setReporter(updatedTask.getReporter());
        existingTask.setLabel(updatedTask.getLabel());
        existingTask.setTimetracking(updatedTask.getTimetracking());
        existingTask.setStartdate(updatedTask.getStartdate());
        existingTask.setCategory(updatedTask.getCategory());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setTaskNumber(updatedTask.getTaskNumber());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setDescription(updatedTask.getDescription());
        return taskRepository.save(existingTask);
    }

    // 更新任务状态
    public Task updateTaskStatus(String id, String newStatus) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }
}

