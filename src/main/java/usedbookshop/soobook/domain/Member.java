package usedbookshop.soobook.domain;

import lombok.Getter;
import usedbookshop.soobook.web.dto.member.ViewMemberDto;

import javax.persistence.*;


@Entity
@Getter
public class Member {


    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="area", column = @Column(name = "home_area"))
            ,@AttributeOverride(name="roadName", column = @Column(name = "home_roadName"))
            ,@AttributeOverride(name="roadCode", column = @Column(name = "home_roadCode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="area", column = @Column(name = "work_area"))
            ,@AttributeOverride(name="roadName", column = @Column(name = "work_roadName"))
            ,@AttributeOverride(name="roadCode", column = @Column(name = "work_roadCode"))
    })
    private Address workAddress;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;


    protected Member() {
    }

    private Member(String name, Address homeAddress, Address workAddress, String email, String password) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.email = email;
        this.password = password;
    }

    public static Member createMember(String name, Address homeAddress, Address workAddress, String email, String password){
        return new Member(name, homeAddress, workAddress, email, password);
    }

}