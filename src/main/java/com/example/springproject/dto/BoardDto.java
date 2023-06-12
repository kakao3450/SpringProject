package com.example.springproject.dto;

import com.example.springproject.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private String title;
    private String contents;
    private String userId;
    private String userName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BoardDto(Board board){
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.userId = board.getUserId();
        this.userName = board.getUserName();
        this.createdAt = board.getCreatedAt();
        this.updatedAt = board.getUpdatedAt();

    }

}