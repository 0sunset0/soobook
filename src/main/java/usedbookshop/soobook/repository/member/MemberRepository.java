package usedbookshop.soobook.repository.member;

import usedbookshop.soobook.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

    Optional<Member> findByEmail(String email);

}
