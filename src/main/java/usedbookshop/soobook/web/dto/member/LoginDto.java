package usedbookshop.soobook.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class LoginDto {

    private String email;
    private String password;

}
