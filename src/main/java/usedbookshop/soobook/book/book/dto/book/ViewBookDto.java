package usedbookshop.soobook.book.book.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.book.book.domain.Book;
import usedbookshop.soobook.book.book.domain.BookStatus;
import usedbookshop.soobook.member.domain.Member;

@Getter
@AllArgsConstructor
public class ViewBookDto {

    private Long id;
    private String title;
    private Long price;
    private Long score;
    private String author;
    private Long quantity;
    private BookStatus bookStatus;
    private Member member;

    //Entity -> Dto
    public static ViewBookDto from(Book book) {
        return new ViewBookDto(book.getId(), book.getTitle(), book.getPrice(),
                book.getScore(), book.getAuthor(), book.getQuantity(),
                book.getBookStatus(), book.getMember());
    }
}
