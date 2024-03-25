package movie.mingle.controller;

import movie.mingle.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import movie.mingle.domain.Member;
import movie.mingle.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/signup", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Member> signUp(@RequestBody MemberDto memberDto) {
        Member createdMember = memberService.signUp(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    // 회원 정보 조회
    @GetMapping("/{memberId}/info")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        Member member = memberService.getMember(memberId);
        return ResponseEntity.ok(member);
    }

    // 회원 정보 수정
    @PutMapping("/{memberId}")
    public ResponseEntity<Member> updateMember(@PathVariable Long memberId, @RequestBody Member updatedMember) {
        Member member = memberService.updateMember(memberId, updatedMember);
        return ResponseEntity.ok(member);
    }

    // 회원 탈퇴
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}



