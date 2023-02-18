package usedbookshop.soobook.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import usedbookshop.soobook.member.Address;
import usedbookshop.soobook.member.Member;

@Getter
@AllArgsConstructor
public class JoinDto {

    private String name;
    private String email;
    private String password;

    private String homeArea;
    private Integer homeRoadCode;
    private String homeRoadName;

    private String workArea;
    private Integer workRoadCode;
    private String workRoadName;

    public static Member toMember(JoinDto joinDto){
        Address homeAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        Address workAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        return Member.createMember(joinDto.getName(), joinDto.getEmail(), new Member.Password(joinDto.getPassword()), homeAddress, workAddress);
    }
}
