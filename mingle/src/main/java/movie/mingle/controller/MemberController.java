package movie.mingle.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/signup")
    public String createForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "members/signup";
    }

}



