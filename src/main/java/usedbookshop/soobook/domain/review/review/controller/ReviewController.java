package usedbookshop.soobook.domain.review.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usedbookshop.soobook.domain.book.book.entity.Book;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.order.order.entity.Order;
import usedbookshop.soobook.domain.order.order.entity.OrderBook;
import usedbookshop.soobook.domain.review.comment.dto.ViewCommentDto;
import usedbookshop.soobook.domain.review.comment.entity.Comment;
import usedbookshop.soobook.domain.review.comment.service.CommentService;
import usedbookshop.soobook.domain.review.review.dto.CreateReviewDto;
import usedbookshop.soobook.domain.review.review.dto.ViewReviewDto;
import usedbookshop.soobook.domain.review.review.entity.ReviewScore;
import usedbookshop.soobook.domain.book.book.service.BookService;
import usedbookshop.soobook.domain.order.order.service.OrderService;
import usedbookshop.soobook.domain.review.review.service.ReviewService;
import usedbookshop.soobook.global.common.argumentresolver.LoginMember;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;
    private final OrderService orderService;
    private final CommentService commentService;

    /**
     * 리뷰 자세히 보기
     */
    @GetMapping("/book/review/detail")
    public String reviewDetail(@RequestParam("reviewId") Long reviewId, Model model){
        ViewReviewDto viewReviewDto = ViewReviewDto.from(reviewService.findReview(reviewId));
        model.addAttribute("viewReviewDto", viewReviewDto);
        List<Comment> commentList = commentService.findCommentsByReview(reviewId);
        List<ViewCommentDto> viewCommentDtos = commentList.stream()
                .map(c -> ViewCommentDto.from(c))
                .collect(Collectors.toList());
        model.addAttribute("viewCommentDtos", viewCommentDtos);
        return "book/review/detail";
    }

    /**
     * 리뷰 작성
     */
    @GetMapping("/book/createReview")
    public String createReviewForm(@RequestParam("bookId") Long bookId, @LoginMember Member loginMember, RedirectAttributes redirectAttributes, Model model ){

        //멤버가 사지 않은 책에 리뷰를 쓰려고 하면 에러 메세지 출력
        Book book = bookService.findBook(bookId);
        List<Order> orderList = orderService.findByMember(loginMember.getId());

        for (Order order : orderList) {
            List<OrderBook> orderBookList = order.getOrderBookList();
            for (OrderBook orderBook : orderBookList) {
                if (orderBook.getBook() == book){
                    model.addAttribute("reviewScores", ReviewScore.values());
                    model.addAttribute("createReviewDto", new CreateReviewDto());
                    return "book/createReview";
                }
            }
        }

        redirectAttributes.addAttribute("bookId", bookId);
        return "redirect:/book/detail";

    }


    @PostMapping("/book/createReview")
    public String createReview(@Valid @ModelAttribute("createReviewDto") CreateReviewDto createReviewDto, BindingResult bindingResult,
                               @LoginMember Member loginMember,
                               @RequestParam("bookId") Long bookId, HttpServletRequest request, Model model){

        //검증 에러 검사
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            model.addAttribute("reviewScores", ReviewScore.values());
            return "book/createReview";
        }

        Book book = bookService.findBook(bookId);
        reviewService.createReview(createReviewDto, book, loginMember);

        return "redirect:/book/detail?bookId=" + bookId;
    }


}
