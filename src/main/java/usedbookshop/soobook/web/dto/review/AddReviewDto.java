package usedbookshop.soobook.web.dto.review;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Comment;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.ReviewScore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AddReviewDto {

    private Long id;
    private String title;
    private String content;
    private ReviewScore score;

}
