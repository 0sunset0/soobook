package usedbookshop.soobook.web.controller.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import usedbookshop.soobook.web.dto.member.JoinDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    @GetMapping("/detail")
    public String reviewDetail(){
        return "review/detail";
    }
}
