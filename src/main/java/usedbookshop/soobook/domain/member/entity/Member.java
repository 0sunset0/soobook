package usedbookshop.soobook.domain.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import usedbookshop.soobook.domain.model.Address;
import usedbookshop.soobook.domain.order.order.entity.Order;
import usedbookshop.soobook.domain.review.comment.entity.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {


    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private Password password;

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

    @Builder
    private Member(String name, Address homeAddress, Address workAddress, String email, Password password) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.email = email;
        this.password = password;
    }

    public static Member createMember(String name, String email, Password password, Address homeAddress, Address workAddress ){
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .homeAddress(homeAddress)
                .workAddress(workAddress)
                .build();
    }

}