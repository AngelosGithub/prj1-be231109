package com.example.prj1be231109.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id;
    private Integer BoardId;
    private String memberId;
    private String comment;
    private LocalDateTime inserted;
}
