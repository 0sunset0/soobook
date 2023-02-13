package usedbookshop.soobook.web.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Book;

@Getter
@AllArgsConstructor
public class AddBookDto {

    private String title;
    private int price;
    private String author;
    private int quantity;
}
