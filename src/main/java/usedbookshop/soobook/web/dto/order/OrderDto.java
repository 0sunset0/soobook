package usedbookshop.soobook.web.dto.order;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Order;

@Getter @Setter
public class OrderDto {



    private String deliveryArea;
    private int deliveryRoadCode;
    private String deliveryRoadName;

    private int quantity;


}
