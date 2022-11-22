package usedbookshop.soobook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.*;
import usedbookshop.soobook.exception.NotEnoughStockException;
import usedbookshop.soobook.repository.order.OrderRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Autowired EntityManager em;

    @Test
    void 주문() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Address deliveryAddress = new Address("인천", "원당대로", 22643);
        Book book = getBook("데이터베이스", 20000, "박다솜", 5);

        // when
        Order order = orderService.order(member.getId(), deliveryAddress, book.getId(), 4);

        // then
        Order findOrder = orderRepository.findById(order.getId());
        Assertions.assertThat(findOrder).isEqualTo(order);
        Assertions.assertThat(book.getQuantity()).isEqualTo(1);

    }

    @Test
    void 주문취소() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Address deliveryAddress = new Address("인천", "원당대로", 22643);
        Book book = getBook("데이터베이스", 20000, "박다솜", 5);
        Order order = orderService.order(member.getId(), deliveryAddress, book.getId(), 4);

        // when
        orderService.cancel(order.getId());

        // then(주문상태 변화(ORDER->CANCEL), book 수량 중가)
        Order findOrder = orderRepository.findById(order.getId());
        Assertions.assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        Assertions.assertThat(book.getQuantity()).isEqualTo(5);

    }
    @Test
    void 재고_없을시_예외() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Address deliveryAddress = new Address("인천", "원당대로", 22643);
        Book book = getBook("데이터베이스", 20000, "박다솜", 5);

        // when

        // then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), deliveryAddress, book.getId(), 6);
        });

    }

    private Book getBook(String title, int price, String author, int quantity) {
        CategoryBook categoryBook = new CategoryBook();
        em.persist(categoryBook);
        Book book = Book.createBook(title, price, author, quantity, categoryBook);
        em.persist(book);
        return book;
    }

    private Member getMember(String name, String email, String password) {
        Address homeAddress = new Address("인천", "원당대로", 1111);
        Address workAddress = new Address("서울", "양화대로", 2222);
        Member member = Member.createMember(name, homeAddress, workAddress, email, password);
        em.persist(member);
        return member;
    }

}