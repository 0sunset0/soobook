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
        return Member.createMember(this.getName(), new Address(homeArea, homeRoadCode, homeRoadName), new Address(workArea, workRoadCode, workRoadName), this.getEmail(), this.password);
    }
}
