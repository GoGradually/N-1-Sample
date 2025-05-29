package com.example.nplusone.practice.repository;

import com.example.nplusone.practice.domain.Daughter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaughterRepository extends JpaRepository<Daughter, Long> {
}
