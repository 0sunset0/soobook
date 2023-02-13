package usedbookshop.soobook.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Comment;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.ReviewScore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class AddReviewDto {

    private String title;
    private String content;
    private ReviewScore score;

}
