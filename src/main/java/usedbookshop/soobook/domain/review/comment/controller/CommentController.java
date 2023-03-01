package usedbookshop.soobook.domain.review.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usedbookshop.soobook.domain.review.review.dto.CreateReviewDto;


@Controller
@RequiredArgsConstructor
public class CommentController {

    @PostMapping("review/addComment")
    public String addComment(@ModelAttribute("addReviewDto") CreateReviewDto addReviewDto, @RequestParam("reviewId") Long reviewId){
        return "redirect:/book/review/detail?reviewId=" + reviewId;
    }
}
