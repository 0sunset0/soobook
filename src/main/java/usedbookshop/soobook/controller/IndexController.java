package usedbookshop.soobook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Component
@Slf4j
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
