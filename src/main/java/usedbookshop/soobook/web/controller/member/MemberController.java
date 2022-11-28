package usedbookshop.soobook.web.controller.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usedbookshop.soobook.domain.*;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.repository.member.MemberRepository;
import usedbookshop.soobook.repository.order.OrderRepository;
import usedbookshop.soobook.repository.review.ReviewRepository;
import usedbookshop.soobook.service.BookService;
import usedbookshop.soobook.service.OrderService;
import usedbookshop.soobook.web.dto.book.ViewBookDto;
import usedbookshop.soobook.web.dto.member.LoginDto;
import usedbookshop.soobook.web.dto.member.JoinDto;
import usedbookshop.soobook.service.MemberService;
import usedbookshop.soobook.web.dto.member.MemberDto;
import usedbookshop.soobook.web.dto.order.ViewOrderDto;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final BookService bookService;
    private final OrderService orderService;


    /**
     * 회원가입
     */
    @GetMapping("/join")
    public String viewJoin(@ModelAttribute("JoinDto") JoinDto joinDto){
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("JoinDto") JoinDto joinDto){
        memberService.join(joinDto);
        return "redirect:/";
    }

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String viewLogin(@ModelAttribute("loginDto") LoginDto loginDto){
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Member loginMember = memberService.login(loginDto.getEmail(), loginDto.getPassword());
        if (loginMember == null){
            redirectAttributes.addAttribute("loginStatus", "fail");
            return "redirect:/member/login";
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

    /**
     * 로그아웃
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    /**
     * 마이페이지
     */
    @GetMapping("/mypage")
    public String mypage(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");
        List<ViewOrderDto> viewOrderDtos = orderService.findByMember(loginMember.getId());
        List<Review> reviewList = reviewRepository.findByMember(loginMember.getId());
        List<ViewBookDto> viewBookDtos = bookService.findByMember(loginMember.getId());

        model.addAttribute("viewOrderDtos", viewOrderDtos);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("viewBookDtos", viewBookDtos);
        return "member/mypage";
    }

}
