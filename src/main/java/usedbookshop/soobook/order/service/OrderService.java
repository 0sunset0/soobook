package usedbookshop.soobook.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.book.book.domain.Book;
import usedbookshop.soobook.book.book.repository.BookRepository;
import usedbookshop.soobook.delivery.DeliveryStatus;
import usedbookshop.soobook.member.domain.Address;
import usedbookshop.soobook.delivery.Delivery;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.order.domain.Order;
import usedbookshop.soobook.order.domain.OrderBook;
import usedbookshop.soobook.order.domain.OrderStatus;
import usedbookshop.soobook.member.repository.MemberRepository;
import usedbookshop.soobook.order.repository.OrderRepository;

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

        Delivery delivery = Delivery.createDelivery(deliveryAddress);

        OrderBook orderBook = new OrderBook(book, book.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderBook);

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

    //주문 정보 보기
    public Order findOrder(Long orderId){
        Order order = orderRepository.findById(orderId);
        return order;
    }

    public List<Order> findByMember(Long memberId) {
        List<Order> ordersByMember = orderRepository.findByMember(memberId);
        return ordersByMember;
    }
}
