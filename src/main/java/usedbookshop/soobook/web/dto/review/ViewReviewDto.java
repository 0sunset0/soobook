package usedbookshop.soobook.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ViewReviewDto {

    private Long id;
    private String title;
    private String content;
    private ReviewScore score;
    private Book book;
    private Member member;
    private List<Comment> commentList = new ArrayList<>();

    public static ViewReviewDto from(Review review){
        return new ViewReviewDto(review.getId(),
                review.getTitle(), review.getContent(), review.getScore(),
                review.getBook(), review.getMember(), review.getCommentList());
    }
}
