package movie.mingle.controller;

import movie.mingle.domain.Member;
import movie.mingle.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("member", new Member()); // 회원 객체를 모델에 추가
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute Member member) {
        memberRepository.save(member); // 회원 저장
        return "success"; // 성공 페이지로 리다이렉트
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }

    @GetMapping("/info/{id}")
    public String showMemberInfo(@PathVariable Long id, Model model) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            model.addAttribute("member", member);
            return "memberInfo";
        } else {
            return "redirect:";
        }
    }

    @GetMapping("/all")
    public String showAllMembers(Model model) {
        List<Member> allMembers = memberRepository.findAll();
        model.addAttribute("members", allMembers);
        return "allMembers";
    }


    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "date";
    }
}



