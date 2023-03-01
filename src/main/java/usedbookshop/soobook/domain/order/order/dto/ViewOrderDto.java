package usedbookshop.soobook.domain.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.domain.order.order.entity.Order;
import usedbookshop.soobook.domain.order.order.entity.OrderBook;
import usedbookshop.soobook.domain.order.order.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ViewOrderDto {

    private Long id;

    private String deliveryArea;
    private Long deliveryRoadCode;
    private String deliveryRoadName;

    private OrderStatus orderStatus;
    private List<OrderBook> orderBookList = new ArrayList<>();
    private LocalDateTime createdDate;


    // Entity -> Dto
    public static ViewOrderDto from(Order order){
        return new ViewOrderDto(order.getId(),
                order.getDelivery().getAddress().getArea(),
                order.getDelivery().getAddress().getRoadCode(),
                order.getDelivery().getAddress().getRoadName(),
                order.getOrderStatus(),
                order.getOrderBookList(),
                order.getCreatedDate()
                );
    }
}
