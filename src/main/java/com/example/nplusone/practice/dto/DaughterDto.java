package com.example.nplusone.practice.dto;

import com.example.nplusone.practice.domain.Daughter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DaughterDto {
    private Long id;
    private String name;

    public DaughterDto(Daughter daughter) {
        this.id = daughter.getId();
        this.name = daughter.getName();
    }
}
