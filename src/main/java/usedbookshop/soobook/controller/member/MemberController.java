package usedbookshop.soobook.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.dto.member.LoginDto;
import usedbookshop.soobook.dto.member.JoinDto;
import usedbookshop.soobook.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @GetMapping("/join")
    public String join(@ModelAttribute("JoinDto") JoinDto joinDto){
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("JoinDto") JoinDto joinDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "member/join";
        }
        memberService.join(joinDto);
        return "redirect:/";
    }

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto){
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return "/member/login";
        }

        Member loginMember = memberService.login(loginDto.getEmail(), loginDto.getPassword());
        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/member/login";
        }

        /**
         * 로그인 성공 처리
         */
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        HttpSession session = request.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute("loginMember", loginMember);

        return "redirect:/";
    }


}
