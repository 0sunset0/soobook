package usedbookshop.soobook.web.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usedbookshop.soobook.domain.*;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.repository.comment.CommentRepository;
import usedbookshop.soobook.repository.member.MemberRepository;
import usedbookshop.soobook.repository.order.OrderRepository;
import usedbookshop.soobook.service.BookService;
import usedbookshop.soobook.service.MemberService;
import usedbookshop.soobook.service.ReviewService;
import usedbookshop.soobook.web.dto.book.AddBookDto;
import usedbookshop.soobook.web.dto.book.ViewBookDto;
import usedbookshop.soobook.web.dto.review.AddReviewDto;
import usedbookshop.soobook.web.dto.review.ViewReviewDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    /**
     * 리뷰 자세히 보기
     */
    @GetMapping("book/review/detail")
    public String reviewDetail(@RequestParam("reviewId") Long reviewId, Model model){
        ViewReviewDto viewReviewDto = reviewService.findReview(reviewId);
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
        ViewBookDto findBookDto = bookService.findBook(bookId);
        Member loginMember = (Member) session.getAttribute("loginMember");
        List<Order> orderList = orderRepository.findByMember(loginMember.getId());

        for (Order order : orderList) {
            List<OrderBook> orderBookList = order.getOrderBookList();
            for (OrderBook orderBook : orderBookList) {
                if (orderBook.getBook().getId().equals(findBookDto.getId())){
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
        Member member = memberRepository.findById(loginMember.getId());
        Book book = bookRepository.findById(bookId);

        reviewService.createReview(Review.createReview(addReviewDto.getTitle(), addReviewDto.getContent(), addReviewDto.getScore(), book, member));

        return "redirect:/book/detail?bookId="+bookId;
    }


}
