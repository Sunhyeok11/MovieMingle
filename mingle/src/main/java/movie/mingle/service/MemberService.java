package movie.mingle.service;

import movie.mingle.domain.Member;
import movie.mingle.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean isUsernameExists(String username) {
        // 사용자 이름 중복 확인 로직
        return memberRepository.existsByUsername(username);
    }

    public boolean isEmailExists(String email) {
        // 이메일 중복 확인 로직
        return memberRepository.existsByEmail(email);
    }

    public void signUp(Member member) {
        // 회원 가입 로직
        memberRepository.save(member);
    }
}


