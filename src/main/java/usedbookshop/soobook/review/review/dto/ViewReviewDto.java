package usedbookshop.soobook.review.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.book.book.Book;
import usedbookshop.soobook.member.Member;
import usedbookshop.soobook.review.comment.Comment;
import usedbookshop.soobook.review.review.Review;
import usedbookshop.soobook.review.review.ReviewScore;

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
