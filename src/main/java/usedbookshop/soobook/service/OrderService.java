package usedbookshop.soobook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.*;
import usedbookshop.soobook.repository.book.BookRepository;
import usedbookshop.soobook.repository.member.MemberRepository;
import usedbookshop.soobook.repository.order.OrderRepository;
import usedbookshop.soobook.repository.order.OrderRepositoryImpl;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;


    //주문
    public Order order(Long memberId, Address deliveryAddress, Long bookId, int count){

        Member member = memberRepository.findById(memberId);
        Book book = bookRepository.findById(bookId);

        Delivery delivery = new Delivery(deliveryAddress);

        OrderBook orderBook = new OrderBook(book, book.getPrice(), count);
        Order order = new Order(member, delivery, orderBook);

        orderRepository.save(order);
        book.reduceQuantity(count);
        return order;

    }

    //주문취소
    public void cancel(Long orderId){
        Order order = orderRepository.findById(orderId);

        if (order.getDelivery().getDeliveryStatus() != DeliveryStatus.READY){
            throw new IllegalStateException("이미 배송된 상품은 취소가 불가능합니디.");
        }
        order.changeOrderStatus(OrderStatus.CANCEL);

        List<OrderBook> orderBookList = order.getOrderBookList();
        for (OrderBook orderBook : orderBookList){
            orderBook.cancel();
        }
    }
}