package usedbookshop.soobook.order.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import usedbookshop.soobook.book.book.domain.Book;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderBook {

    @Id @GeneratedValue
    @Column(name = "order_book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    public OrderBook(Book book, int orderPrice, int count) {
        this.book = book;
        this.orderPrice = orderPrice;
        this.count = count;
    }


    /**
     * 비즈니스 로직
     */
    //주문한 상품 취소
    public void cancel() {
        getBook().increaseQuantity(count);
    }
}
