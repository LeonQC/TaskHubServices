package com.junlinc.rest.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class TaskHistory {
    private String changeId;
    private String fieldChanged;
    private String oldValue;
    private String newValue;
    private String userId;
    private LocalDateTime changedDate;
}
