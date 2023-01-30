package usedbookshop.soobook.web.controller.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.Review;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.service.BookService;
import usedbookshop.soobook.service.ReviewService;
import usedbookshop.soobook.web.dto.book.AddBookDto;
import usedbookshop.soobook.web.dto.book.ViewBookDto;
import usedbookshop.soobook.web.dto.review.ViewReviewDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final BookService bookService;
    private final ReviewService reviewService;

    /**
     * 모든 책 보기
     */
    @GetMapping("/books")
    public String books(Model model){
        List<ViewBookDto> viewBookDtos = bookService.findAllBooks();
        model.addAttribute("viewBookDtos", viewBookDtos);
        return "books/all";
    }

    /**
     * 책 자세히 보기
     */
    @GetMapping("/book/detail")
    public String detail(@RequestParam("bookId") Long bookId, Model model){
        ViewBookDto viewBookDto = bookService.findBook(bookId);
        model.addAttribute("viewBookDto", viewBookDto);

        List<ViewReviewDto> viewReviewDtos = reviewService.findByBook(bookId);
        model.addAttribute("viewReviewDtos", viewReviewDtos);
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
    public String addBook(@ModelAttribute("addBookDto") AddBookDto addBookDto, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");

        bookService.saveBook(addBookDto, loginMember);

        return "redirect:/books";
    }

}
