package usedbookshop.soobook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.web.dto.member.JoinDto;
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
        JoinDto joinDto = getMemberDto("노을", "1234@naver.com", "1234");

        //when
        Long savedId = memberService.join(joinDto);

        //then
        assertEquals(joinDto.getEmail(), memberRepository.findById(savedId).getEmail());
    }


    @Test
    void 중복회원검사() {
        // given
        JoinDto memberDto1 = getMemberDto("노을1", "sunset@naver.com", "1234");
        JoinDto memberDto2 = getMemberDto("노을2", "sunset@naver.com", "1234");

        // when
        memberService.join(memberDto1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(memberDto2));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 가입된 메일입니다.");
    }

    @Test
    void 로그인() {
        // given
        JoinDto memberDto = getMemberDto("노을", "abcd@naver.com", "1111");

        // when
        memberService.join(memberDto);
        Member loginMember = memberService.login(memberDto.getEmail(), memberDto.getPassword());

        // then
        Assertions.assertThat(loginMember).isNotNull();

    }

    private JoinDto getMemberDto(String name, String email, String password) {
        JoinDto memberDto = new JoinDto();
        memberDto.setName(name);
        memberDto.setEmail(email);
        memberDto.setPassword(password);
        return memberDto;
    }

}