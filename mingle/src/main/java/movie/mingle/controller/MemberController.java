package movie.mingle.controller;

import movie.mingle.domain.Member;
import movie.mingle.domain.Product;
import movie.mingle.repository.MemberRepository;
import movie.mingle.repository.ProductRepository;
import movie.mingle.service.MemberService;
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
    private final MemberService memberService;

    private final ProductRepository productRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository, MemberService memberService, ProductRepository productRepository) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.productRepository = productRepository;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("member", new Member()); // 회원 객체를 모델에 추가
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute Member member, Model model) {
        // 사용자 이름과 이메일 중복 검사 수행
        if (memberService.isUsernameExists(member.getUsername())) {
            model.addAttribute("usernameError", "사용자 이름이 이미 존재합니다.");
            return "signup"; // 사용자 이름 중복 시 회원 가입 폼으로 리다이렉트
        }
        if (memberService.isEmailExists(member.getEmail())) {
            model.addAttribute("emailError", "이메일이 이미 존재합니다.");
            return "signup"; // 이메일 중복 시 회원 가입 폼으로 리다이렉트
        }

        // 중복되지 않은 경우 회원 가입 진행
        memberService.signUp(member);
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

    @GetMapping("/products/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "productForm";
    }

    @PostMapping("/products")
    public String createProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products/new";
    }

    @GetMapping("/productList")
    public String showProductList(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "productList";
    }

    @GetMapping("/order")
    public String showOrderForm(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "orderForm";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam("productId") Long productId,
                             @RequestParam("quantity") int quantity,
                             Model model) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            // 주문 처리 코드
            // 여기서 주문을 처리하거나 다른 로직을 추가할 수 있습니다.
            // 이 예시에서는 단순히 주문 정보를 출력합니다.
            model.addAttribute("product", product);
            model.addAttribute("quantity", quantity);
            return "orderConfirmation";
        } else {
            return "redirect:/order";
        }
    }
}



