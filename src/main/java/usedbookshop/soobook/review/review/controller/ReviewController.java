package usedbookshop.soobook.review.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usedbookshop.soobook.book.book.domain.Book;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.order.order.domain.Order;
import usedbookshop.soobook.order.order.domain.OrderBook;
import usedbookshop.soobook.review.comment.Comment;
import usedbookshop.soobook.review.review.domain.ReviewScore;
import usedbookshop.soobook.review.comment.repository.CommentRepository;
import usedbookshop.soobook.book.book.service.BookService;
import usedbookshop.soobook.member.service.MemberService;
import usedbookshop.soobook.order.order.service.OrderService;
import usedbookshop.soobook.review.review.service.ReviewService;
import usedbookshop.soobook.review.review.dto.AddReviewDto;
import usedbookshop.soobook.review.review.dto.ViewReviewDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;
    private final OrderService orderService;
    private final MemberService memberService;
    private final CommentRepository commentRepository;

    /**
     * 리뷰 자세히 보기
     */
    @GetMapping("book/review/detail")
    public String reviewDetail(@RequestParam("reviewId") Long reviewId, Model model){
        ViewReviewDto viewReviewDto = ViewReviewDto.from(reviewService.findReview(reviewId));
        model.addAttribute("viewReviewDto", viewReviewDto);
        List<Comment> commentList = commentRepository.findByReview(reviewId);
        model.addAttribute("commentList", commentList);
        return "book/review/detail";
    }

    /**
     * 리뷰 작성
     */
    @GetMapping("book/addReview")
    public String addReviewForm(@RequestParam("bookId") Long bookId, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model ){

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }

        //멤버가 사지 않은 책에 리뷰를 쓰려고 하면 에러 메세지 출력
        Book book = bookService.findBook(bookId);
        Member loginMember = (Member) session.getAttribute("loginMember");
        List<Order> orderList = orderService.findByMember(loginMember.getId());

        //TODO 서비스에서 엔티티 반환하는 걸로 바꾸면서 이 부분도 수정 필요
        for (Order order : orderList) {
            List<OrderBook> orderBookList = order.getOrderBookList();
            for (OrderBook orderBook : orderBookList) {
                if (orderBook.getBook().getId().equals(book.getId())){
                    model.addAttribute("reviewScores", ReviewScore.values());
                    return "book/addReview";
                }
            }
        }
        redirectAttributes.addAttribute("bookId", bookId);
        return "redirect:/book/detail";

    }


    @PostMapping("book/addReview")
    public String addReview(@ModelAttribute("addReviewDto") AddReviewDto addReviewDto, @RequestParam("bookId") Long bookId, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");
        Member member = memberService.findMember(loginMember.getId());
        Book book = bookService.findBook(bookId);
        reviewService.createReview(addReviewDto, book, member);

        return "redirect:/book/detail?bookId="+bookId;
    }


}