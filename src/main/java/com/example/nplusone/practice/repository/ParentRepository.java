package com.example.nplusone.practice.repository;

import com.example.nplusone.practice.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    /**
     * 다수 조회 + 그 다수에서 연관관계 하나가 더 있을 때
     * 다수 조회는 1번의 쿼리로 가능한데
     * 그 다수에서 이어지는 다른 연관관계가 하나 더 있으면, 그 연관관계당 하나씩 쿼리가 나간다.
     * N+1 문제는 연관관계를 기준으로, N개를 1번에 조회했다가 N개에서 다시 m개(1~x)를 조회하는데 1번의 쿼리를 더 써서, 1+N번 쿼리를 날리는걸 의미한다.
     * @return
     */
    @Query("select p from Parent p join fetch p.childList c")
    List<Parent> findAllByFetch();

}
