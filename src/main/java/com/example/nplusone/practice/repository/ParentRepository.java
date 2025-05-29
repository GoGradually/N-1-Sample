package com.example.nplusone.practice.repository;

import com.example.nplusone.practice.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    /**
     * select p from Parent p join fetch p.sonList s
     * 다수 조회 + 그 다수에서 연관관계 하나가 더 있을 때
     * 다수 조회는 1번의 쿼리로 가능한데
     * 그 다수에서 이어지는 다른 연관관계가 하나 더 있으면, 그 연관관계당 하나씩 쿼리가 나간다.
     * --------------
     * select p from Parent p join fetch p.sonList s join fetch p.grandParent gp
     * N+1 문제는 연관관계를 기준으로, N개를 1번에 조회했다가 N개에서 다시 m개(1~x)를 조회하는데 1번의 쿼리를 더 써서, 1+N번 쿼리를 날리는걸 의미한다.
     * - 만약 Many To One 관계라면, 1차캐시에 이미 조회되어 있을테니, 쿼리가 한번만 더 나가는거고,
     * - One To Many 관계라면, 1차캐시에 영속상태로 존재하지 않으므로, 각각에 대해서 모두 쿼리가 날아간다.
     * - 따라서 Many To One 관계라면, 쿼리가 1+1회가 되고
     * - One To Many 관계라면, 쿼리가 1+N회가 된다.
     * --------------
     * select p from Parent p join fetch p.sonList s join fetch p.grandParent gp join fetch p.daughterList d
     * Multibag Exception이 발생한다.
     * - 두개의 컬렉션 간의 카테시안 곱 발생
     * - son * daughter
     * - 이는 fetch join만의 문제가 아닌, 조인 연산에 따른 카테시안 곱에 의해 발생하는 문제이다.
     * - 이 문제는 fetchType.EAGER에서도 동일하게 발생한다.
     * ----------------
     * select p from Parent p join fetch p.sonList s join fetch p.grandParent gp join fetch s.grandSonList gs
     * Parent P1
     *     Son S1
     *         GrandSon gs1
     *         GrandSon gs1
     *         GrandSon gs2
     *     Son S2
     *         GrandSon gs3
     * (P1, S2, A1)과 같은 의미없는 데이터는 생성되진 않음 - 카테시안 곱은 아님
     * 기본적으로 Fetch Join은 각 ROW별로 한번만 읽어들여서, O(N)만에 객체 매핑을 수행하는것을 지향
     * Bag(Unordered List)이 두개 이상이 되면, 단순 ROW별로 한번만 읽어들여서 객체 매핑이 불가능해짐
     * O(NlogN)만에 객체-매핑하는 동작은 지원하지 않음
     * 따라서 객체-매핑 순서를 적절히 정해서 객체화하고 싶다면,
     * - 직접 DTO Projection을 통해 조립하거나,
     * - Set과 같은 자료구조로 bag이 2개 이상 생기지 않도록 제어
     * @return
     */
    @Query("select p from Parent p join fetch p.sonList s join fetch p.grandParent gp join fetch s.grandSonList gs")
    List<Parent> findAllByFetch();

}
