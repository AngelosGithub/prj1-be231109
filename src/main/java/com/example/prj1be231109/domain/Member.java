package com.example.prj1be231109.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private String id;
    private String password;
    private String email;
    private LocalDateTime inserted;
}
