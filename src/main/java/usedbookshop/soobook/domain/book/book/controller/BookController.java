package usedbookshop.soobook.domain.book.book.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import usedbookshop.soobook.domain.book.book.entity.Book;
import usedbookshop.soobook.domain.book.book.dto.book.CreateBookDto;
import usedbookshop.soobook.domain.book.book.dto.book.ViewBookDto;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.review.review.dto.ViewReviewDto;
import usedbookshop.soobook.domain.review.review.service.ReviewService;
import usedbookshop.soobook.domain.review.review.entity.Review;
import usedbookshop.soobook.domain.book.book.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
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
    @GetMapping("/books/createBook")
    public String createBookForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        model.addAttribute("createBookDto", new CreateBookDto());
        return "books/createBook";
    }

    @PostMapping("/books/createBook")
    public String createBook(@Valid @ModelAttribute("createBookDto") CreateBookDto createBookDto, BindingResult bindingResult, HttpServletRequest request){
        //검증 에러 검사
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "books/createBook";
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");

        bookService.saveBook(createBookDto, loginMember);
        return "redirect:/books";
    }

}
