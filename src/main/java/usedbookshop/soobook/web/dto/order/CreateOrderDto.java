package usedbookshop.soobook.web.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateOrderDto {


    private String deliveryArea;
    private int deliveryRoadCode;
    private String deliveryRoadName;

    private int quantity;


}
