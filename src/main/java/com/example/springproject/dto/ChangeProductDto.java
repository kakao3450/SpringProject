package com.example.springproject.dto;

public class ChangeProductDto {
    private Long number;
    private String name;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChangeProductDto(Long number, String name) {
        this.number = number;
        this.name = name;
    }
}
