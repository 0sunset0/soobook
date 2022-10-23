package usedbookshop.soobook.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column = @Column(name = "home_city"))
            ,@AttributeOverride(name="street", column = @Column(name = "home_street"))
            ,@AttributeOverride(name="zipcode", column = @Column(name = "home_zipcode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column = @Column(name = "work_city"))
            ,@AttributeOverride(name="street", column = @Column(name = "work_street"))
            ,@AttributeOverride(name="zipcode", column = @Column(name = "work_zipcode"))
    })
    private Address workAddress;

    private String email;
    private String password;

    private Long sunset;
}