package movie.mingle.service;

import movie.mingle.dto.MemberDto;
import org.springframework.stereotype.Service;
import movie.mingle.repository.MemberRepository;
import movie.mingle.domain.Member;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입 로직
    public Member signUp(MemberDto memberDto) {
        Member member = new Member();
        member.setUsername(memberDto.getUsername());
        member.setEmail(memberDto.getEmail());
        member.setPhoneNumber(memberDto.getPhoneNumber());

        // 회원 정보 저장
        return memberRepository.save(member);
    }

    // 회원 정보 조회 로직
    public Member getMember(Long memberId) {
        // 회원 정보 조회
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    // 회원 정보 수정 로직
    public Member updateMember(Long memberId, Member updatedMember) {
        // 기존 회원 정보 조회
        Member existingMember = getMember(memberId);
        // 수정된 회원 정보 업데이트
        existingMember.setUsername(updatedMember.getUsername());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setPhoneNumber(updatedMember.getPhoneNumber());
        // 업데이트된 회원 정보 저장
        return memberRepository.save(existingMember);
    }

    // 회원 탈퇴 로직
    public void deleteMember(Long memberId) {
        // 회원 정보 삭제
        memberRepository.deleteById(memberId);
    }
}
