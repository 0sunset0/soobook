package usedbookshop.soobook.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usedbookshop.soobook.member.Address;
import usedbookshop.soobook.member.Member;
import usedbookshop.soobook.order.Order;
import usedbookshop.soobook.order.repository.OrderRepository;
import usedbookshop.soobook.order.service.OrderService;
import usedbookshop.soobook.order.dto.CreateOrderDto;
import usedbookshop.soobook.order.dto.ViewOrderDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/order")
    public String orderForm(@RequestParam("bookId") Long bookId, Model model){
        model.addAttribute("bookId", bookId);
        return "order/createOrder";

    }

    @PostMapping("/order")
    public String order(@ModelAttribute("createOrderDto") CreateOrderDto createOrderDto,
                        @RequestParam("bookId") Long bookId, HttpServletRequest request, RedirectAttributes redirectAttributes){
        // 로그인 회원인지 검사
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }
        Member loginMember = (Member) session.getAttribute("loginMember");

        // 폼에서 배송 정보 받기 -> 주문 처리
        Address deliveryAddress = Address.createAddress(createOrderDto.getDeliveryArea(), createOrderDto.getDeliveryRoadCode(), createOrderDto.getDeliveryRoadName());
        Order order = orderService.order(loginMember.getId(), deliveryAddress, bookId, createOrderDto.getQuantity());

        // 주문 성공 -> 주문 상세 폼으로 보여주기
        redirectAttributes.addAttribute("orderId", order.getId());
        return "redirect:/order/detail";
    }

    @GetMapping("/order/detail")
    public String detail(@RequestParam("orderId") Long orderId, Model model){

        ViewOrderDto viewOrderDto = ViewOrderDto.from(orderService.findOrder(orderId));
        model.addAttribute("viewOrderDto", viewOrderDto);

        return "order/detail";

    }


    @GetMapping("/order/cancel")
    public String cancel(@RequestParam("orderId") Long orderId, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "redirect:/member/login";
        }

        Member loginMember = (Member) session.getAttribute("loginMember");

        Order order = orderRepository.findById(orderId);
        if (order.getMember().getId()==loginMember.getId()){
            orderService.cancel(orderId);
        }

        return "redirect:/member/mypage";
    }


}
