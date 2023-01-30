package usedbookshop.soobook.web.dto.book;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ViewBookDto {

    private Long id;
    private String title;
    private int price;
    private int score;
    private String author;
    private int quantity;
    private BookStatus bookStatus;
    private Member member;

    //Entity -> Dto
    public static ViewBookDto from(Book book) {
        ViewBookDto viewBookDto = new ViewBookDto();
        viewBookDto.setId(book.getId());
        viewBookDto.setTitle(book.getTitle());
        viewBookDto.setPrice(book.getPrice());
        viewBookDto.setScore(book.getScore());
        viewBookDto.setAuthor(book.getAuthor());
        viewBookDto.setQuantity(book.getQuantity());
        viewBookDto.setBookStatus(book.getBookStatus());
        viewBookDto.setMember(book.getMember());
        return viewBookDto;
    }
}
