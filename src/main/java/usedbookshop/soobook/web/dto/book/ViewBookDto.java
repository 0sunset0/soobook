package usedbookshop.soobook.web.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ViewBookDto {

    private Long id;
    private String title;
    private Integer price;
    private Integer score;
    private String author;
    private Integer quantity;
    private BookStatus bookStatus;
    private Member member;

    //Entity -> Dto
    public static ViewBookDto from(Book book) {
        return new ViewBookDto(book.getId(), book.getTitle(), book.getPrice(),
                book.getScore(), book.getAuthor(), book.getQuantity(),
                book.getBookStatus(), book.getMember());
    }
}
