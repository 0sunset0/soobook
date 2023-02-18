package usedbookshop.soobook.member;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String area;
    private String roadName;
    private Integer roadCode;

    private Address(String area, Integer roadCode, String roadName) {
        this.area = area;
        this.roadName = roadName;
        this.roadCode = roadCode;
    }

    public static Address createAddress(String area, Integer roadCode, String roadName) {
        return new Address(area, roadCode, roadName);
    }
}
