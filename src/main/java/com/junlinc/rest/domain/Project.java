package com.junlinc.rest.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "projects")
@Getter
@Setter
public class Project {
    @Id
    private String id;
    private String projectName;
    private String description;
    private List<String> columns;
    private List<Task> tasks;
    private List<String> invitedUsers;


}
