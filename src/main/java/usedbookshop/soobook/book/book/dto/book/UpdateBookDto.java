package usedbookshop.soobook.book.book.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import usedbookshop.soobook.book.category.CategoryBook;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UpdateBookDto {

    @NotNull
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    @Range(min = 0L)
    private Long price;

    @NotBlank
    private String author;

    @NotNull
    @Range(min = 0L)
    private Long quantity;

    @NotNull
    private CategoryBook categoryBook;

}
