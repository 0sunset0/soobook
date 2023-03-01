package usedbookshop.soobook.domain.order.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class CreateOrderDto {

    @NotBlank
    private String deliveryArea;

    @NotNull
    private Long deliveryRoadCode;

    @NotBlank
    private String deliveryRoadName;

    @NotNull
    private Long quantity;

}
