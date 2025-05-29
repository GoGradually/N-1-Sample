package com.example.nplusone.practice.service;

import com.example.nplusone.practice.repository.ChildRepository;
import com.example.nplusone.practice.repository.ParentRepository;
import com.example.nplusone.practice.domain.Child;
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
    private final ChildRepository childRepository;

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
        for (Long i = 1L; i <= 1000L; i++) {
            Parent parent = new Parent("parent: " + i);
            parents.add(parent);
        }

        parentRepository.saveAll(parents);
    }

    @Transactional
    public void makeChild() {
        List<Child> childList = new ArrayList<>();
        for (long j = 1L; j <= 1000L; j++) {
            Parent parent = parentRepository.findById(j).orElseThrow(() -> new RuntimeException("error"));
            for (int i = 0; i < 100; i++) {
                Child child = new Child("child: " + i);
                child.setParent(parent);
                childList.add(child);
            }
        }
        childRepository.saveAll(childList);
    }
}
