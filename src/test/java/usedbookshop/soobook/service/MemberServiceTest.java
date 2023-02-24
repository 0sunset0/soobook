package usedbookshop.soobook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.member.service.MemberService;
import usedbookshop.soobook.member.dto.JoinDto;
import usedbookshop.soobook.member.repository.MemberRepository;
import usedbookshop.soobook.member.dto.LoginDto;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        JoinDto joinDto = getJoinDto("노을", "1234@naver.com", "1234");

        //when
        Long savedId = memberService.join(joinDto);

        //then
        assertEquals(joinDto.getEmail(), memberRepository.findById(savedId).getEmail());
    }


    @Test
    void 중복회원검사() {
        // given
        JoinDto memberDto1 = getJoinDto("노을1", "sunset@naver.com", "1234");
        JoinDto memberDto2 = getJoinDto("노을2", "sunset@naver.com", "1234");

        // when
        memberService.join(memberDto1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(memberDto2));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 가입된 메일입니다.");
    }

    @Test
    void 로그인() {
        // given
        JoinDto joinDto = getJoinDto("노을", "abcd@naver.com", "1111");
        LoginDto loginDto = getLoginDto("abcd@naver.com", "1111");

        // when
        memberService.join(joinDto);
        Member loginMember = memberService.login(loginDto);

        // then
        Assertions.assertThat(loginMember).isNotNull();

    }

    private LoginDto getLoginDto(String email, String password) {
        return new LoginDto(email, password);
    }

    private JoinDto getJoinDto(String name, String email, String password) {
        return new JoinDto(name, email, password, "서울", 111L, "양화대로", "서울", 111L, "마포대로");
    }

}