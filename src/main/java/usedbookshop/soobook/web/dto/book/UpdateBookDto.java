package usedbookshop.soobook.web.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.domain.CategoryBook;

@Getter
@AllArgsConstructor
public class UpdateBookDto {

    private Long id;
    private String title;
    private int price;
    private String author;
    private int quantity;
    private CategoryBook categoryBook;

}
