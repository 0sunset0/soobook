package usedbookshop.soobook.web.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.CategoryBook;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.service.MemberService;
import usedbookshop.soobook.web.dto.member.JoinDto;
import usedbookshop.soobook.web.dto.member.LoginDto;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    /**
     * 모든 책 보기
     */
    @GetMapping("/books")
    public String books(){
        return "book/books";
    }

    /**
     * 책 한 권 보기
     */
    @GetMapping("/book/detail")
    public String detail(){
        return "book/detail";
    }

    /**
     * 책 등록하기
     */
    @GetMapping("/book/addBook")
    public String addBook(){
        return "book/addBook";
    }




}
