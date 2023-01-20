package usedbookshop.soobook.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Address {

    private String area;
    private String roadName;
    private int roadCode;

    protected Address() {
    }

    private Address(String area, int roadCode, String roadName) {
        this.area = area;
        this.roadName = roadName;
        this.roadCode = roadCode;
    }

    public static Address createAddress(String area, int roadCode, String roadName) {
        return new Address(area, roadCode, roadName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getRoadCode() == address.getRoadCode() && Objects.equals(getArea(), address.getArea()) && Objects.equals(getRoadName(), address.getRoadName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getArea(), getRoadName(), getRoadCode());
    }
}
