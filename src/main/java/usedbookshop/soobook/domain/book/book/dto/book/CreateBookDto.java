package usedbookshop.soobook.domain.book.book.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CreateBookDto {

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
}
