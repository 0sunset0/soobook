package usedbookshop.soobook.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.CategoryBook;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.web.dto.member.MemberDto;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@Component
@RequiredArgsConstructor
public class IndexController {

    private final BookRepository bookRepository;

    @RequestMapping("/")
    public String index(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model ){

        //베스트셀러 10권 가져오기
        List<Book> bestSellers = bookRepository.findBest10Books();
        model.addAttribute("bestSellers", bestSellers);

        if(loginMember==null){
            return "index";
        }
        MemberDto memberDto = loginMember.toMemberDto();
        model.addAttribute("memberDto", memberDto);
        return "indexForMember";


    }

}
