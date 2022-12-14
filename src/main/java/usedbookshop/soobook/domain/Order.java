package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.web.dto.order.ViewOrderDto;

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

    private Order(Member member, Delivery delivery, OrderBook... orderBookList) {
        this.member = member;
        this.delivery = delivery;
        for (OrderBook orderBook : orderBookList){
            this.addOrderBook(orderBook);
        }
    }

    protected Order() {
    }

    /**
     * 주문 정적 팩토리 메서드
     */
    public static Order createOrder(Member member, Delivery delivery, OrderBook... orderBookList) {
        Order order = new Order(member, delivery, orderBookList);
        order.createdDate = LocalDateTime.now();
        order.orderStatus = OrderStatus.ORDER;
        return order;
    }

    /**
     * 연관관계 메서드
     */
    private void addOrderBook(OrderBook orderBook) {
        orderBook.setOrder(this);
        orderBookList.add(orderBook);
    }


    /**
     * ViewOrderDto로 변환
     */
    public ViewOrderDto toViewOrderDto(){
        ViewOrderDto viewOrderDto = new ViewOrderDto();
        viewOrderDto.setId(this.id);
        viewOrderDto.setDeliveryArea(this.delivery.getAddress().getArea());
        viewOrderDto.setDeliveryRoadCode(this.delivery.getAddress().getRoadCode());
        viewOrderDto.setDeliveryRoadName(this.delivery.getAddress().getRoadName());
        viewOrderDto.setOrderBookList(this.orderBookList);
        viewOrderDto.setCreatedDate(this.createdDate);
        viewOrderDto.setOrderStatus(this.orderStatus);

        return viewOrderDto;
    }



}
