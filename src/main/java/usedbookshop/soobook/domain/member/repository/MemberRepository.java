package usedbookshop.soobook.domain.member.repository;

import usedbookshop.soobook.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

    List<Member> findAll();

    Optional<Member> findByEmail(String email);

}
