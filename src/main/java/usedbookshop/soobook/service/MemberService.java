package usedbookshop.soobook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.domain.Member;
import usedbookshop.soobook.dto.member.JoinDto;
import usedbookshop.soobook.repository.member.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    //회원가입
    public Long join(JoinDto joinDto){
        //Dto -> domain
        Member member = joinDto.toEntity();
        //중복메일 검증
        checkDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void checkDuplicateMember(Member member) {

        Optional<Member> result = memberRepository.findByEmail(member.getEmail());
        if (result.isPresent()){
            throw new IllegalStateException("이미 가입된 메일입니다.");
        }
    }

    public Member login(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }


    //회원 조회
    @Transactional(readOnly = true)
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

}
