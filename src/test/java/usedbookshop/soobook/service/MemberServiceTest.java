package usedbookshop.soobook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.Address;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.repository.member.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        Address homeAddress = new Address("인천", "원당대로", 1111);
        Address workAddress = new Address("경기도", "와우안길", 2222);
        Member member = new Member("노을", homeAddress, workAddress, "sunset@naver.com", "1234");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(savedId));
    }

    @Test
    void 중복회원검사() {
        // given
        Address homeAddress1 = new Address("인천", "원당대로", 1111);
        Address workAddress1 = new Address("경기도", "와우안길", 2222);
        Member member1 = new Member("노을1", homeAddress1, workAddress1, "sunset@naver.com", "1234");

        Address homeAddress2 = new Address("인천", "원당대로", 1111);
        Address workAddress2= new Address("경기도", "와우안길", 2222);
        Member member2 = new Member("노을2", homeAddress2, workAddress2, "sunset@naver.com", "1234");


        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 가입된 메일입니다.");
    }

    @Test
    void 로그인() {
        // given
        Address homeAddress = new Address("인천", "원당대로", 1111);
        Address workAddress = new Address("경기도", "와우안길", 2222);
        Member member = new Member("노을", homeAddress, workAddress, "sunset@naver.com", "1234");

        // when
        memberService.join(member);
        Member loginMember = memberService.login(member.getEmail(), member.getPassword());

        // then
        Assertions.assertThat(loginMember).isNotNull();

    }

}