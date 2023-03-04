package usedbookshop.soobook.domain.book.book.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.domain.book.book.entity.Book;
import usedbookshop.soobook.domain.book.book.entity.BookStatus;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.review.review.dto.ViewReviewDto;
import usedbookshop.soobook.domain.review.review.entity.Review;

import java.util.List;

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
