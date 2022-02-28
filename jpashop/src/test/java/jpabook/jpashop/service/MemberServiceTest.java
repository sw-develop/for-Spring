package jpabook.jpashop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.respository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository memberRepository;

  @Test
  public void 회원가입() throws Exception {
    //given
    Member member = new Member();
    member.setName("memberA");

    //when
    Long savedId = memberService.join(member);

    //then
    assertEquals(member, memberService.findOne(savedId));
  }

  @Test(expected = IllegalStateException.class)
  public void 중복_회원_예외() throws Exception {
    //given
    Member member1 = new Member();
    member1.setName("memberA");

    Member member2 = new Member();
    member2.setName("memberA");

    //when
    memberService.join(member1);
    memberService.join(member2);

    //then
    fail("테스트 실패: 예외가 발생해야 한다.");
  }
}
