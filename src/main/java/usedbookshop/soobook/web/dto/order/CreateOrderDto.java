package usedbookshop.soobook.web.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CreateOrderDto {

    private String deliveryArea;
    private int deliveryRoadCode;
    private String deliveryRoadName;
    private int quantity;

}
