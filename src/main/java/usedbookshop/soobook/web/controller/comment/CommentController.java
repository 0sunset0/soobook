package usedbookshop.soobook.web.controller.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Review;
import usedbookshop.soobook.web.dto.review.AddReviewDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CommentController {

    @PostMapping("review/addComment")
    public String addComment(@ModelAttribute("addReviewDto") AddReviewDto addReviewDto, @RequestParam("reviewId") Long reviewId){

        return "redirect:/book/review/detail?reviewId=" + reviewId;
    }
}
