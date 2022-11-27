package usedbookshop.soobook.web.controller.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.CategoryBook;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Review;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.service.BookService;
import usedbookshop.soobook.service.MemberService;
import usedbookshop.soobook.web.dto.book.BookDto;
import usedbookshop.soobook.web.dto.member.JoinDto;
import usedbookshop.soobook.web.dto.member.LoginDto;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    /**
     * 모든 책 보기
     */
    @GetMapping("/books")
    public String books(Model model){
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books/all";
    }

    /**
     * 책 자세히 보기
     */
    // TODO : bookId 넘겨받는 것 보안에 취약하므로 수정해야 함.
    @GetMapping("/book/detail")
    public String detail(@RequestParam("bookId") Long bookId, Model model){
        Book book = bookService.findBook(bookId);
        model.addAttribute("book", book);
        List<Review> reviewList = book.getReviewList();
        model.addAttribute("reviewList", reviewList);
        return "book/detail";
    }

    /**
     * 책 등록하기
     */
    @GetMapping("/books/addBook")
    public String addBookForm(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        return "books/addBook";
    }

    @PostMapping("/books/addBook")
    public String addBook(@ModelAttribute("bookDto") BookDto bookDto, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");

        bookService.saveBook(Book.createBook(bookDto.getTitle(), bookDto.getPrice(), bookDto.getAuthor(),
                bookDto.getQuantity(), loginMember));

        return "redirect:/books";
    }


}
