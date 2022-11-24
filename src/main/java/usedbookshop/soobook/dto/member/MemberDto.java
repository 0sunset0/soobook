package usedbookshop.soobook.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Address;
import usedbookshop.soobook.domain.Member;

@Getter @Setter
public class MemberDto {

    private String name;

    private Address homeAddress;

    private Address workAddress;

    private String email;

    private String password;

    public Member toEntity(){
        return Member.createMember(this.getName(), this.getHomeAddress(), this.getWorkAddress(), this.getEmail(), this.password);
    }
}
