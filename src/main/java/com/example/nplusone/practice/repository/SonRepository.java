package com.example.nplusone.practice.repository;

import com.example.nplusone.practice.domain.Son;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SonRepository extends JpaRepository<Son, Long> {
}
