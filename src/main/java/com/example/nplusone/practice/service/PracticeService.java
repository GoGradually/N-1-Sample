package com.example.nplusone.practice.service;

import com.example.nplusone.practice.domain.Daughter;
import com.example.nplusone.practice.domain.GrandParent;
import com.example.nplusone.practice.domain.Son;
import com.example.nplusone.practice.repository.DaughterRepository;
import com.example.nplusone.practice.repository.SonRepository;
import com.example.nplusone.practice.repository.GrandParentRepository;
import com.example.nplusone.practice.repository.ParentRepository;
import com.example.nplusone.practice.domain.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PracticeService {
    private final ParentRepository parentRepository;
    private final SonRepository sonRepository;
    private final GrandParentRepository grandParentRepository;
    private final DaughterRepository daughterRepository;

    @Transactional(readOnly = true)
    public List<Parent> getParentsByNormal() {
        return parentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Parent> getParentsByFetch() {
        return parentRepository.findAllByFetch();
    }

    @Transactional
    public void makeParent() {
        List<Parent> parents = new ArrayList<>();
        GrandParent grandParent = new GrandParent("grandParent");
        for (long i = 1L; i <= 1000L; i++) {
            Parent parent = new Parent("parent: " + i);
            parent.setGrandParent(grandParent);
            parents.add(parent);
        }

        grandParentRepository.save(grandParent);
        parentRepository.saveAll(parents);
    }

    @Transactional
    public void makeChild() {
        List<Son> sonList = new ArrayList<>();
        List<Daughter> daughterList = new ArrayList<>();
        for (long j = 1L; j <= 1000L; j++) {
            Parent parent = parentRepository.findById(j).orElseThrow(() -> new RuntimeException("error"));
            for (int i = 0; i < 100; i++) {
                Son son = new Son("son: " + i);
                son.setParent(parent);
                sonList.add(son);

                Daughter daughter = new Daughter("daughter: " + i);
                daughter.setParent(parent);
                daughterList.add(daughter);
            }
        }
        sonRepository.saveAll(sonList);
        daughterRepository.saveAll(daughterList);
    }
}
