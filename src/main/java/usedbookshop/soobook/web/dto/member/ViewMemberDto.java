package usedbookshop.soobook.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Member;

@Getter
@AllArgsConstructor
public class ViewMemberDto {

    private String name;

    private String homeArea;
    private int homeRoadCode;
    private String homeRoadName;

    private String workArea;
    private int workRoadCode;
    private String workRoadName;

    private String email;

    //Entity -> Dto
    public static ViewMemberDto from(Member member) {
        return new ViewMemberDto(member.getName(), member.getHomeAddress().getArea(),
                member.getWorkAddress().getRoadCode(), member.getWorkAddress().getRoadName(), member.getWorkAddress().getArea(),
                member.getWorkAddress().getRoadCode(), member.getWorkAddress().getRoadName(), member.getEmail());
    }
}
