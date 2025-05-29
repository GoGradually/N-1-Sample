package com.example.nplusone.practice.dto;

import com.example.nplusone.practice.domain.Parent;
import lombok.Getter;

@Getter
public class ParentDto {
    private Long id;
    private String name;
    private int sonCount;
    private int daughterCount;
    private String grandParentName;
    public ParentDto(Parent parent) {
        this.id = parent.getId();
        this.name = parent.getName();
        this.sonCount = parent.getSonList().size();
        this.daughterCount = parent.getDaughterList().size();
        this.grandParentName = parent.getGrandParent().getName();
    }
}
