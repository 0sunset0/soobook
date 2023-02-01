package usedbookshop.soobook.web.dto.book;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.CategoryBook;

@Getter
@Setter
public class UpdateBookDto {

    private Long id;
    private String title;
    private int price;
    private String author;
    private int quantity;
    private CategoryBook categoryBook;

}
