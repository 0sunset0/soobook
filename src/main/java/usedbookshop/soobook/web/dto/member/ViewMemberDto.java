package usedbookshop.soobook.web.dto.member;

import lombok.Getter;
import lombok.Setter;
import usedbookshop.soobook.domain.Book;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.web.dto.book.ViewBookDto;

@Getter @Setter
public class ViewMemberDto {

    private String name;

    private String homeArea;
    private int homeRoadCode;
    private String homeRoadName;

    private String workArea;
    private int workRoadCode;
    private String workRoadName;

    private String email;

    private String password;

    //Entity -> Dto
    public static ViewMemberDto from(Member member) {
        ViewMemberDto viewMemberDto = new ViewMemberDto();
        viewMemberDto.setName(member.getName());
        viewMemberDto.setHomeArea(member.getHomeAddress().getArea());
        viewMemberDto.setHomeRoadCode(member.getWorkAddress().getRoadCode());
        viewMemberDto.setWorkRoadName(member.getWorkAddress().getRoadName());
        viewMemberDto.setWorkArea(member.getWorkAddress().getArea());
        viewMemberDto.setWorkRoadCode(member.getWorkAddress().getRoadCode());
        viewMemberDto.setWorkRoadName(member.getWorkAddress().getRoadName());
        return viewMemberDto;
    }

}
