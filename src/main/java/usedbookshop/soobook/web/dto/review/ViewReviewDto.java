package usedbookshop.soobook.web.dto.review;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ViewReviewDto {

    private Long id;

    private String title;

    private String content;

    private ReviewScore score;

    private Book book;

    private Member member;

    private List<Comment> commentList = new ArrayList<>();

    public static ViewReviewDto from(Review review){
        ViewReviewDto viewReviewDto = new ViewReviewDto();
        viewReviewDto.setId(review.getId());
        viewReviewDto.setTitle(review.getTitle());
        viewReviewDto.setContent(review.getContent());
        viewReviewDto.setScore(review.getScore());
        viewReviewDto.setBook(review.getBook());
        viewReviewDto.setMember(review.getMember());
        viewReviewDto.setCommentList(review.getCommentList());
        return viewReviewDto;

    }


}
