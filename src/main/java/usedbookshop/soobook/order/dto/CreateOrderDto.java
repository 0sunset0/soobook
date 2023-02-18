package usedbookshop.soobook.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateOrderDto {

    private String deliveryArea;
    private int deliveryRoadCode;
    private String deliveryRoadName;
    private int quantity;

}
