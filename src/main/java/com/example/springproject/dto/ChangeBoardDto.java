package com.example.springproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeBoardDto {

    private String contents;

    public ChangeBoardDto(String contents) {
        this.contents = contents;
    }
}
