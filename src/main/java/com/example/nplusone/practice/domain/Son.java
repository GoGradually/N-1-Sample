package com.example.nplusone.practice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Son {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Parent parent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "son")
    private List<GrandSon> grandSonList = new ArrayList<>();

    public Son() {
    }

    public Son(String s) {
        name = s;
    }
}
