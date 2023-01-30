package usedbookshop.soobook.web.dto.order;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ViewOrderDto {

    private Long id;

    private String deliveryArea;
    private int deliveryRoadCode;
    private String deliveryRoadName;

    private OrderStatus orderStatus;
    private List<OrderBook> orderBookList = new ArrayList<>();
    private LocalDateTime createdDate;


    // Entity -> Dto
    public static ViewOrderDto from(Order order){

        ViewOrderDto viewOrderDto = new ViewOrderDto();
        viewOrderDto.setId(order.getId());
        viewOrderDto.setDeliveryArea(order.getDelivery().getAddress().getArea());
        viewOrderDto.setDeliveryRoadCode(order.getDelivery().getAddress().getRoadCode());
        viewOrderDto.setDeliveryRoadName(order.getDelivery().getAddress().getRoadName());
        viewOrderDto.setOrderBookList(order.getOrderBookList());
        viewOrderDto.setCreatedDate(order.getCreatedDate());
        viewOrderDto.setOrderStatus(order.getOrderStatus());

        return viewOrderDto;
    }
}
