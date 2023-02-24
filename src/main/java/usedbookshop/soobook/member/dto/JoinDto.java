package usedbookshop.soobook.member.dto;

import lombok.*;
import usedbookshop.soobook.member.domain.Address;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.member.domain.Password;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {

    @NotBlank
    private String name;

    @Email @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank
    private String homeArea;
    @NotNull
    private Long homeRoadCode;
    @NotBlank
    private String homeRoadName;

    @NotBlank
    private String workArea;
    @NotNull
    private Long workRoadCode;
    @NotBlank
    private String workRoadName;

    public static Member toMember(JoinDto joinDto){
        Address homeAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        Address workAddress = Address.createAddress(joinDto.getHomeArea(), joinDto.getHomeRoadCode(), joinDto.getHomeRoadName());
        return Member.createMember(joinDto.getName(), joinDto.getEmail(), new Password(joinDto.getPassword()), homeAddress, workAddress);
    }
}
