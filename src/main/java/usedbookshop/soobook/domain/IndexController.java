package usedbookshop.soobook.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import usedbookshop.soobook.domain.book.book.entity.Book;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.book.book.service.BookService;
import usedbookshop.soobook.domain.book.book.dto.book.ViewBookDto;
import usedbookshop.soobook.domain.member.dto.ViewMemberDto;
import usedbookshop.soobook.global.common.argumentresolver.LoginMember;

import java.util.ArrayList;
import java.util.List;

@Controller
@Component
@RequiredArgsConstructor
@Slf4j
public class IndexController {

    private final BookService bookService;

    @RequestMapping("/")
    public String index(@LoginMember Member loginMember, Model model){

        //베스트셀러 10권 가져오기
        List<Book> best10Books = bookService.findBest10Books();
        List<ViewBookDto> best10BookDtos = new ArrayList<>();
        for (Book book : best10Books) {
            best10BookDtos.add(ViewBookDto.from(book));
        }
        model.addAttribute("best10BookDtos", best10BookDtos);

        if(loginMember == null){
            return "index";
        }

        ViewMemberDto viewMemberDto = ViewMemberDto.from(loginMember);
        model.addAttribute("memberDto", viewMemberDto);
        return "indexForMember";

    }

}
