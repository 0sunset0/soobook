package usedbookshop.soobook.repository.member;

import usedbookshop.soobook.domain.Member;

import java.util.List;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

    List<Member> findByEmail(String email);
}
