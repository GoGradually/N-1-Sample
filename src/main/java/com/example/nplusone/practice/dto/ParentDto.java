package com.example.nplusone.practice.dto;

import com.example.nplusone.practice.domain.Parent;
import lombok.Getter;

@Getter
public class ParentDto {
    private Long id;
    private String name;
    private int childCount;
    private String grandParentName;
    public ParentDto(Parent parent) {
        this.id = parent.getId();
        this.name = parent.getName();
        this.childCount = parent.getChildList().size();
        this.grandParentName = parent.getGrandParent().getName();
    }
}
