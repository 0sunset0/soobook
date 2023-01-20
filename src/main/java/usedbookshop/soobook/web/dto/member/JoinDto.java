package usedbookshop.soobook.web.dto.member;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Address;
import usedbookshop.soobook.domain.Member;

@Getter @Setter
public class JoinDto {

    private String name;

    private String homeArea;
    private int homeRoadCode;
    private String homeRoadName;

    private String workArea;
    private int workRoadCode;
    private String workRoadName;

    private String email;

    private String password;

    public Member toEntity(){
        Address homeAddress = Address.createAddress(homeArea, homeRoadCode, homeRoadName);
        Address workAddress = Address.createAddress(workArea, workRoadCode, workRoadName);
        return Member.createMember(this.getName(), homeAddress, workAddress, this.getEmail(), this.password);
    }
}
