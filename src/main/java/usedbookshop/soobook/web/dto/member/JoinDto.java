package usedbookshop.soobook.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Address;
import usedbookshop.soobook.domain.Member;

@Getter
@AllArgsConstructor
public class JoinDto {

    private String name;
    private String email;
    private String password;

    private String homeArea;
    private int homeRoadCode;
    private String homeRoadName;

    private String workArea;
    private int workRoadCode;
    private String workRoadName;

    public static Member toMember(JoinDto joinDto){
        Address homeAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        Address workAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        return Member.createMember(joinDto.getName(), joinDto.getEmail(), new Member.Password(joinDto.getPassword()), homeAddress, workAddress);
    }
}
