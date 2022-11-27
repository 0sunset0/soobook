package usedbookshop.soobook.web.dto.member;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Address;
import usedbookshop.soobook.domain.Member;
@Getter @Setter
public class MemberDto {

    private String name;

    private String homeArea;
    private int homeRoadCode;
    private String homeRoadName;

    private String workArea;
    private int workRoadCode;
    private String workRoadName;

    private String email;

    private String password;

}
