package com.example.nplusone.practice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private final List<Son> sonList = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private final List<Daughter> daughterList = new ArrayList<>();

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private GrandParent grandParent;

    public Parent() {
    }

    public Parent(String name) {
        this.name = name;
    }

}
