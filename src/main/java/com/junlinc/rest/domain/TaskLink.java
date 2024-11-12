package com.junlinc.rest.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskLink {
    private String linkedTaskId;
    private String relationType;
    private String title;
}
