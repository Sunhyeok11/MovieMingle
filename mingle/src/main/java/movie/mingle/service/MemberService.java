package movie.mingle.service;

import movie.mingle.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import movie.mingle.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean isUsernameExists(String username) {
        return memberRepository.existsByUsername(username);
    }

    public boolean isEmailExists(String email) {
        return memberRepository.existsByEmail(email);
    }

    public void signUp(Member member) {
        memberRepository.save(member);
    }
}

