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

    public static Member toMember(JoinDto joinDto){
        Address homeAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        Address workAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        return Member.createMember(joinDto.getName(), homeAddress, workAddress, joinDto.getEmail(), joinDto.getPassword());
    }
}
