package usedbookshop.soobook.web.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usedbookshop.soobook.domain.*;
import usedbookshop.soobook.repository.comment.CommentRepository;
import usedbookshop.soobook.repository.order.OrderRepository;
import usedbookshop.soobook.service.BookService;
import usedbookshop.soobook.service.ReviewService;
import usedbookshop.soobook.web.dto.book.ViewBookDto;

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

    /**
     * 리뷰 자세히 보기
     */
    @GetMapping("book/review/detail")
    public String reviewDetail(@ModelAttribute Review review, Model model){
        model.addAttribute("review", review);
        List<Comment> commentList = commentRepository.findByReview(review.getId());
        model.addAttribute("commentList", commentList);
        return "review/detail";
    }

    /**
     * 리뷰 작성
     */
    //TODO : 테스트 해야 함.
    @GetMapping("book/addReview")
    public String addReviewForm(@RequestParam("viewBookDtoId") Long bookId, HttpServletRequest request, RedirectAttributes redirectAttributes ){
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
                    return "book/addReview";
                }
            }
        }
        redirectAttributes.addAttribute("bookId", bookId);
        return "redirect:/book/detail";

    }


    @PostMapping("book/addReview")
    public String addReview(HttpServletRequest request){
        return "/";

    }


}
