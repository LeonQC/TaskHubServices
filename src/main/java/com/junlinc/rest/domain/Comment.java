package com.junlinc.rest.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Comment {
    private String commentId;
    private String userId;
    private String comment;
    private LocalDateTime createdDate;
}
