package usedbookshop.soobook.delivery;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import usedbookshop.soobook.member.Address;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private Delivery(Address address) {
        this.address = address;
        this.deliveryStatus = DeliveryStatus.READY;
    }

    public static Delivery createDelivery(Address address) {
        return new Delivery(address);
    }

}