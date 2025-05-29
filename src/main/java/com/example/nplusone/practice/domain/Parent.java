package com.example.nplusone.practice.domain;

import jakarta.persistence.*;
import lombok.Getter;

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
    private final List<Child> childList = new ArrayList<>();

    public Parent() {
    }

    public Parent(String name) {
        this.name = name;
    }

    public void addChild(Child child) {
        childList.add(child);
    }
}
