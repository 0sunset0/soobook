package usedbookshop.soobook.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.book.book.domain.Book;
import usedbookshop.soobook.book.category.CategoryBook;
import usedbookshop.soobook.member.domain.Address;
import usedbookshop.soobook.exception.NotEnoughStockException;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.member.domain.Password;
import usedbookshop.soobook.order.order.domain.Order;
import usedbookshop.soobook.order.order.domain.OrderStatus;
import usedbookshop.soobook.order.order.service.OrderService;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired EntityManager em;

    @Test
    void 주문() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Address deliveryAddress = Address.createAddress("인천", 22643L, "원당대로");
        Book book = getBook("데이터베이스", 20000L, "박다솜", 5L, member);

        // when
        Order order = orderService.order(member.getId(), deliveryAddress, book.getId(), 4L);

        // then
        Order findOrder = orderService.findOrder(order.getId());
        Assertions.assertThat(findOrder).isEqualTo(order);
        Assertions.assertThat(book.getQuantity()).isEqualTo(1);

    }

    @Test
    void 주문취소() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Address deliveryAddress = Address.createAddress("인천", 22643L, "원당대로");
        Book book = getBook("데이터베이스", 20000L, "박다솜", 5L, member);
        Order order = orderService.order(member.getId(), deliveryAddress, book.getId(), 4L);

        // when
        orderService.cancel(order.getId());

        // then(주문상태 변화(ORDER->CANCEL), book 수량 중가)
        Order findOrder = orderService.findOrder(order.getId());
        Assertions.assertThat(findOrder.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        Assertions.assertThat(book.getQuantity()).isEqualTo(5);

    }
    @Test
    void 재고_없을시_예외() {
        // given
        Member member = getMember("노을", "1234@naver.com", "1234");
        Address deliveryAddress = Address.createAddress("인천", 22643L, "원당대로");
        Book book = getBook("데이터베이스", 20000L, "박다솜", 5L, member);

        // when

        // then
        assertThrows(NotEnoughStockException.class, () -> {
            orderService.order(member.getId(), deliveryAddress, book.getId(), 6L);
        });

    }

    private Book getBook(String title, Long price, String author, Long quantity, Member member) {
        CategoryBook categoryBook = new CategoryBook();
        em.persist(categoryBook);
        Book book = Book.createBook(title, price, author, quantity, member);
        em.persist(book);
        return book;
    }

    private Member getMember(String name, String email, String password) {
        Address homeAddress = Address.createAddress("인천", 2222L, "원당대로");
        Address workAddress = Address.createAddress("인천", 1111L, "원당대로");
        Member member = Member.createMember(name, email, new Password(password), homeAddress, workAddress);
        em.persist(member);
        return member;
    }

}