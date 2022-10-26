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


    public Address(String area, String roadName, int roadCode) {
        this.area = area;
        this.roadName = roadName;
        this.roadCode = roadCode;
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
