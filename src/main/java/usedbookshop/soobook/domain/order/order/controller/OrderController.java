package usedbookshop.soobook.domain.order.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usedbookshop.soobook.domain.member.entity.Member;
import usedbookshop.soobook.domain.order.order.dto.CreateOrderDto;
import usedbookshop.soobook.domain.order.order.dto.ViewOrderDto;
import usedbookshop.soobook.domain.order.order.entity.Order;
import usedbookshop.soobook.domain.order.order.repository.OrderRepository;
import usedbookshop.soobook.domain.model.Address;
import usedbookshop.soobook.domain.order.order.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/order")
    public String orderForm(@RequestParam("bookId") Long bookId, Model model){
        model.addAttribute("createOrderDto", new CreateOrderDto());
        return "order/createOrder";

    }

    @PostMapping("/order")
    public String order(@Valid @ModelAttribute("createOrderDto") CreateOrderDto createOrderDto, BindingResult bindingResult,
                        @RequestParam("bookId") Long bookId, HttpServletRequest request, RedirectAttributes redirectAttributes){

        //검증 에러 검사
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "order/createOrder";
        }

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
