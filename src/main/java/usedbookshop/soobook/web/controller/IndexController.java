package usedbookshop.soobook.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.CategoryBook;
import usedbookshop.soobook.repository.book.BookRepository;

import javax.annotation.PostConstruct;

@Controller
@Component
@Slf4j
@RequiredArgsConstructor
public class IndexController {

    private final BookRepository bookRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
