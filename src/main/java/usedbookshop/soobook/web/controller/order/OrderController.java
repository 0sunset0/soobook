package usedbookshop.soobook.web.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import usedbookshop.soobook.domain.Order;
import usedbookshop.soobook.service.OrderService;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order")
    public String orderForm(){
        return "order/createOrder";

    }

    @PostMapping("/order")
    public String order(){
        // 로그인 회원인지 검사
        // 폼에서 배송 정보 받기 -> 객체 만들기
        // 주문한 bookId로 OrderBook 객체 만들기
        // 주문 성공 -> 주문 상세 폼으로 보여주기
        return "order/orderDetail";
    }
}
