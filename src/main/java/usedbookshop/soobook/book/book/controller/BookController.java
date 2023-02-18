package usedbookshop.soobook.book.book.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import usedbookshop.soobook.book.book.domain.Book;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.review.review.domain.Review;
import usedbookshop.soobook.book.book.service.BookService;
import usedbookshop.soobook.review.review.service.ReviewService;
import usedbookshop.soobook.book.book.dto.book.AddBookDto;
import usedbookshop.soobook.book.book.dto.book.ViewBookDto;
import usedbookshop.soobook.review.review.dto.ViewReviewDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    /**
     * 모든 책 보기
     */
    @GetMapping("/books")
    public String books(Model model){
        List<Book> allBooks = bookService.findAllBooks();
        List<ViewBookDto> viewBookDtos = new ArrayList<>();
        for (Book book : allBooks) {
            viewBookDtos.add(ViewBookDto.from(book));
        }
        model.addAttribute("viewBookDtos", viewBookDtos);
        return "books/all";
    }

    /**
     * 책 자세히 보기
     */
    @GetMapping("/book/detail")
    public String detail(@RequestParam("bookId") Long bookId, Model model){
        ViewBookDto viewBookDto = ViewBookDto.from(bookService.findBook(bookId));
        model.addAttribute("viewBookDto", viewBookDto);

        List<Review> reviewsBtBook = reviewService.findByBook(bookId);
        List<ViewReviewDto> viewReviewDtos = new ArrayList<>();
        for (Review review : reviewsBtBook) {
            viewReviewDtos.add(ViewReviewDto.from(review));
        }
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
