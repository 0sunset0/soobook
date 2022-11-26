package usedbookshop.soobook.web.dto.book;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.BookStatus;
import usedbookshop.soobook.domain.CategoryBook;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BookDto {

    private String title;
    private int price;
    private int score;
    private String author;
    private int quantity;
}
