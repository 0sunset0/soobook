package usedbookshop.soobook.member.dto;

import lombok.*;
import usedbookshop.soobook.member.domain.Address;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.member.domain.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class JoinDto {

    @NotBlank
    private String name;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String homeArea;
    @NotNull
    private Integer homeRoadCode;
    @NotBlank
    private String homeRoadName;

    @NotBlank
    private String workArea;
    @NotNull
    private Integer workRoadCode;
    @NotBlank
    private String workRoadName;

    public static Member toMember(JoinDto joinDto){
        Address homeAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        Address workAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        return Member.createMember(joinDto.getName(), joinDto.getEmail(), new Password(joinDto.getPassword()), homeAddress, workAddress);
    }
}
