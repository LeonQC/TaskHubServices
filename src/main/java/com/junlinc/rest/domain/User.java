package com.junlinc.rest.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String status;
    private LocalDateTime lastActive;
    private String userCurrentAccess;

    private List<String> ownedProjects;
    private List<String> assignedTasks;
}
