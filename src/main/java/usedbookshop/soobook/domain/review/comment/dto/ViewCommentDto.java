package usedbookshop.soobook.domain.review.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.domain.review.comment.entity.Comment;
import usedbookshop.soobook.domain.review.review.dto.ViewReviewDto;
import usedbookshop.soobook.domain.review.review.entity.Review;

@Getter
@AllArgsConstructor
public class ViewCommentDto {

    private String memberName;
    private String content;

    public static ViewCommentDto from(Comment comment){
        return new ViewCommentDto(comment.getMember().getName(), comment.getContent());
    }
}
