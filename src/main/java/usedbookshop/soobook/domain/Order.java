package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order extends Date{

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderBook> orderBookList = new ArrayList<>();

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 주문 생성자
     */
    public Order(Member member, Delivery delivery, OrderBook... orderBookList) {
        this.createdDate = LocalDateTime.now();
        this.member = member;
        this.delivery = delivery;
        this.orderStatus = OrderStatus.ORDER;
        for (OrderBook orderBook : orderBookList){
            this.addOrderBook(orderBook);
        }
    }
    /**
     * 연관관계 메서드
     */
    private void addOrderBook(OrderBook orderBook) {
        orderBook.setOrder(this);
        orderBookList.add(orderBook);
    }




}
