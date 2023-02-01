package usedbookshop.soobook.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String area;
    private String roadName;
    private int roadCode;

    private Address(String area, int roadCode, String roadName) {
        this.area = area;
        this.roadName = roadName;
        this.roadCode = roadCode;
    }

    public static Address createAddress(String area, int roadCode, String roadName) {
        return new Address(area, roadCode, roadName);
    }
}
