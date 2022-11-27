package usedbookshop.soobook.web.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddBookDto {

    private String title;
    private int price;
    private String author;
    private int quantity;
}
