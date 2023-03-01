package usedbookshop.soobook.domain.review.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import usedbookshop.soobook.domain.review.review.entity.ReviewScore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreateReviewDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private ReviewScore score;

}
