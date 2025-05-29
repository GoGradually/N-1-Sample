package com.example.nplusone.practice.dto;

import com.example.nplusone.practice.domain.Child;
import lombok.Getter;

@Getter
public class ChildDto {
    private Long id;
    private String name;

    public ChildDto(Child child) {
        this.id = child.getId();
        this.name = child.getName();
    }
}
