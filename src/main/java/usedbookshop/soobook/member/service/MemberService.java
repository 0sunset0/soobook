package usedbookshop.soobook.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import usedbookshop.soobook.member.domain.Member;
import usedbookshop.soobook.member.dto.JoinDto;
import usedbookshop.soobook.member.repository.MemberRepository;
import usedbookshop.soobook.member.dto.LoginDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    //회원가입
    public Long join(JoinDto joinDto){
        //Dto -> domain
        Member member = JoinDto.toMember(joinDto);
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

    public Member login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        return memberRepository.findByEmail(email)
                .filter(m -> m.getPassword().isMatched(password))
                .orElse(null);
    }


    //회원 조회
    @Transactional(readOnly = true)
    public Member findMember(Long memberId){
        return memberRepository.findById(memberId);
    }

}
