package com.jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    //저장
    public Long save(Member member){
        em.persist(member);
        return member.getId(); //Id 값 반환
    }

    //조회
    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
