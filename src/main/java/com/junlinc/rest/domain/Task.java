package com.junlinc.rest.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.lang.annotation.Documented;
import java.util.List;
@Getter
@Setter
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private String assignee;
    private String reporter;
    private String label;
    private String timetracking;
    private String startdate;
    private String category;
    private String dueDate;
    private String taskNumber;
    private String priority;
    private String status;
    private String column;

    private List<String> attachments;
    private List<Task> childIssues;
    private List<TaskLink> links;
    private List<Comment> comments;
    private List<TaskHistory> history;
    private List<String> owners;
}


