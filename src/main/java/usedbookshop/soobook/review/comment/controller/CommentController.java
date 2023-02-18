package usedbookshop.soobook.review.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import usedbookshop.soobook.review.review.dto.AddReviewDto;


@Controller
@RequiredArgsConstructor
public class CommentController {

    @PostMapping("review/addComment")
    public String addComment(@ModelAttribute("addReviewDto") AddReviewDto addReviewDto, @RequestParam("reviewId") Long reviewId){
        return "redirect:/book/review/detail?reviewId=" + reviewId;
    }
}
