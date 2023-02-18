package usedbookshop.soobook.order;

import lombok.*;
import usedbookshop.soobook.common.Date;
import usedbookshop.soobook.delivery.Delivery;
import usedbookshop.soobook.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends Date {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBook> orderBookList = new ArrayList<>();

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Builder
    private Order(Member member, Delivery delivery, OrderBook... orderBookList) {
        this.member = member;
        this.delivery = delivery;
        for (OrderBook orderBook : orderBookList){
            this.addOrderBook(orderBook);
        }
        this.createdDate = LocalDateTime.now();
        this.orderStatus = OrderStatus.ORDER;
    }


    /**
     * 주문 정적 팩토리 메서드
     */
    public static Order createOrder(Member member, Delivery delivery, OrderBook... orderBookList) {
        return Order.builder()
                .member(member)
                .delivery(delivery)
                .orderBookList(orderBookList)
                .build();
    }

    /**
     * 연관관계 메서드
     */
    private void addOrderBook(OrderBook orderBook) {
        orderBook.setOrder(this);
        orderBookList.add(orderBook);
    }


}
