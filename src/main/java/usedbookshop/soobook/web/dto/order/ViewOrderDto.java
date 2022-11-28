package usedbookshop.soobook.web.dto.order;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Delivery;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.domain.OrderBook;
import usedbookshop.soobook.domain.OrderStatus;

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
}
