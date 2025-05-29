package com.example.nplusone.practice;

import org.springframework.data.jpa.repository.JpaRepository;

interface ChildRepository extends JpaRepository<Child, Long> {
}
