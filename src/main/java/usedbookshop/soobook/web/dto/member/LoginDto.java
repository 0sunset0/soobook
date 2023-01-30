package usedbookshop.soobook.web.dto.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Address;
import usedbookshop.soobook.domain.Member;

@Getter @Setter
public class LoginDto {

    private String email;

    private String password;


}
