package movie.mingle.controller;

import movie.mingle.domain.Member;
import movie.mingle.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam String username,
                         @RequestParam String email,
                         @RequestParam String phoneNumber,
                         @RequestParam String password) {
        Member member = new Member();
        member.setUsername(username);
        member.setEmail(email);
        member.setPhoneNumber(phoneNumber);
        member.setPassword(password);
        memberRepository.save(member); // 회원 저장
        return "success"; // 성공 페이지로 리다이렉트
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "success";
    }

    @GetMapping("test")
    public String test(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "test";
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "date";
    }
}



