package usedbookshop.soobook;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import usedbookshop.soobook.book.book.domain.Book;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.book.book.service.BookService;
import usedbookshop.soobook.book.book.dto.book.ViewBookDto;
import usedbookshop.soobook.member.dto.ViewMemberDto;

import java.util.ArrayList;
import java.util.List;

@Controller
@Component
@RequiredArgsConstructor
public class IndexController {

    private final BookService bookService;

    @RequestMapping("/")
    public String index(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model ){

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
