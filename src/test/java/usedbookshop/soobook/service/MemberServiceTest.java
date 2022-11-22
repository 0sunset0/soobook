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
        Member member = getMember("노을", "sunset@naver.com", "1234");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(savedId));
    }

    @Test
    void 중복회원검사() {
        // given
        Member member1 = getMember("노을1", "sunset@naver.com", "1234");
        Member member2 = getMember("노을2", "sunset@naver.com", "1234");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 가입된 메일입니다.");
    }

    @Test
    void 로그인() {
        // given
        Member member = getMember("노을", "abcd@naver.com", "1111");

        // when
        memberService.join(member);
        Member loginMember = memberService.login(member.getEmail(), member.getPassword());

        // then
        Assertions.assertThat(loginMember).isNotNull();

    }
    private Member getMember(String name, String email, String password) {
        Address homeAddress = new Address("인천", "원당대로", 1111);
        Address workAddress = new Address("서울", "양화대로", 2222);
        Member member = Member.createMember(name, homeAddress, workAddress, email, password);
        return member;
    }

}