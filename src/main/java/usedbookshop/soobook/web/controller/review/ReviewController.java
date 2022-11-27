package usedbookshop.soobook.web.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Review;
import usedbookshop.soobook.repository.review.ReviewRepository;
import usedbookshop.soobook.service.ReviewService;
import usedbookshop.soobook.web.dto.book.BookDto;
import usedbookshop.soobook.web.dto.member.JoinDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 자세히 보기
     */
    @GetMapping("book/review/detail")
    public String reviewDetail(@ModelAttribute Review review){
        return "review/detail";
    }

    /**
     * 리뷰 작성
     */
    @GetMapping("book/addReview")
    public String addReviewForm(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        return "book/addReview";
    }


}
