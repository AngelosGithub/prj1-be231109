package com.example.prj1be231109.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private Integer id;
    private String title;
    private String content;
    private String writer;
    private String nickName;
    private LocalDateTime inserted;
    private Integer countComment;
}
