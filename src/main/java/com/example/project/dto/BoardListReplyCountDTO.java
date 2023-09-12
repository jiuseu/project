package com.example.project.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO {

    private Long bno;
    private String title;
    private String user;
    private LocalDateTime regDate;

    private Long replyCount;
}
