package com.example.nplusone.practice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class GrandSon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "son_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Son son;

    public GrandSon() {
    }

    public GrandSon(String name) {
        this.name = name;
    }
}
