package usedbookshop.soobook.web.dto.member;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {

    private String email;

    private String password;
}
