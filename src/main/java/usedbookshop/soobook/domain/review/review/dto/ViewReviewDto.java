package usedbookshop.soobook.domain.review.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.domain.book.book.entity.Book;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.review.comment.entity.Comment;
import usedbookshop.soobook.domain.review.review.entity.Review;
import usedbookshop.soobook.domain.review.review.entity.ReviewScore;

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
    private String author;
    private List<Comment> commentList;

    public static ViewReviewDto from(Review review){
        return new ViewReviewDto(review.getId(),
                review.getTitle(), review.getContent(), review.getScore(),
                review.getBook(), review.getMember().getName(), review.getCommentList());
    }
}
