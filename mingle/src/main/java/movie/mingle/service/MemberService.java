package movie.mingle.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import movie.mingle.repository.MemberRepository;
import movie.mingle.domain.Member;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional //변경
    public Long join(Member member) {

        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
