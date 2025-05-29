package com.example.nplusone.practice.dto;

import com.example.nplusone.practice.domain.Son;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SonDto {
    private Long id;
    private String name;

    public SonDto(Son son) {
        this.id = son.getId();
        this.name = son.getName();
    }
}
