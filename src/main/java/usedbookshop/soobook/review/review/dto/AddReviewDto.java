package usedbookshop.soobook.review.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.review.review.ReviewScore;

@Getter
@AllArgsConstructor
public class AddReviewDto {

    private String title;
    private String content;
    private ReviewScore score;

}
