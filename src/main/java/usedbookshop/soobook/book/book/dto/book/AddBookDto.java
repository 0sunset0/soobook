package usedbookshop.soobook.book.book.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddBookDto {

    private String title;
    private int price;
    private String author;
    private int quantity;
}
