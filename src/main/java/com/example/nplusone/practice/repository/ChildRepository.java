package com.example.nplusone.practice.repository;

import com.example.nplusone.practice.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
}
