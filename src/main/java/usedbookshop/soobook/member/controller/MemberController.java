package usedbookshop.soobook.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usedbookshop.soobook.book.book.domain.Book;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.order.order.domain.Order;
import usedbookshop.soobook.review.review.domain.Review;
import usedbookshop.soobook.book.book.service.BookService;
import usedbookshop.soobook.order.order.service.OrderService;
import usedbookshop.soobook.review.review.service.ReviewService;
import usedbookshop.soobook.book.book.dto.book.ViewBookDto;
import usedbookshop.soobook.member.dto.LoginDto;
import usedbookshop.soobook.member.dto.JoinDto;
import usedbookshop.soobook.member.service.MemberService;
import usedbookshop.soobook.order.order.dto.ViewOrderDto;
import usedbookshop.soobook.review.review.dto.ViewReviewDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final BookService bookService;
    private final OrderService orderService;
    private final ReviewService reviewService;


    /**
     * 회원가입
     */
    @GetMapping("/join")
    public String viewJoin(Model model){
        model.addAttribute("joinDto", new JoinDto());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute("joinDto") JoinDto joinDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "member/join";
        }
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
        Member loginMember = memberService.login(loginDto);
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

        List<Order> ordersByMember = orderService.findByMember(loginMember.getId());
        List<ViewOrderDto> viewOrderDtos = new ArrayList<>();
        for (Order order : ordersByMember) {
            viewOrderDtos.add(ViewOrderDto.from(order));
        }

        List<Review> reviewsByMember = reviewService.findByMember(loginMember.getId());
        List<ViewReviewDto> viewReviewDtos = new ArrayList<>();
        for (Review review : reviewsByMember) {
            viewReviewDtos.add(ViewReviewDto.from(review));
        }
        List<Book> booksByMember = bookService.findByMember(loginMember.getId());
        List<ViewBookDto> viewBookDtos = new ArrayList<>();
        for (Book book : booksByMember) {
            viewBookDtos.add(ViewBookDto.from(book));
        }
        model.addAttribute("viewOrderDtos", viewOrderDtos);
        model.addAttribute("viewReviewDtos", viewReviewDtos);
        model.addAttribute("viewBookDtos", viewBookDtos);
        return "member/mypage";
    }

}
