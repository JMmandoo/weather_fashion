package com.min.fashion.member.controller;


import com.min.fashion.member.dto.MemberDTO;
import com.min.fashion.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
  // 생성자 주입
  private final MemberService memberService;

  // 회원가입 페이지 출력 요청
  @GetMapping("/user/member/save")
  public String saveForm() {
    return "user/save";
  }

  @PostMapping("/user/member/save")
  public String save(@ModelAttribute MemberDTO memberDTO) {
    System.out.println("MemberController.save");
    System.out.println("memberDTO = " + memberDTO);
    memberService.save(memberDTO);
    return "user/login";
  }

  @GetMapping("/user/member/login")
  public String loginForm() {
    return "user/login";
  }

  @PostMapping("/member/login")
  public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
    MemberDTO loginResult = memberService.login(memberDTO);
    if (loginResult != null) {
      // login 성공
      session.setAttribute("loginEmail", loginResult.getMemberEmail());
      return "user/main";
    } else {
      // login 실패
      return "user/login";
    }
  }

  @GetMapping("/user/member/")
  public String findAll(Model model) {
    List<MemberDTO> memberDTOList = memberService.findAll();
    // 어떠한 html로 가져갈 데이터가 있다면 model사용
    model.addAttribute("memberList", memberDTOList);
    return "user/list";
  }

  @GetMapping("/user/member/{id}")
  public String findById(@PathVariable Long id, Model model) {
    MemberDTO memberDTO = memberService.findById(id);
    model.addAttribute("member", memberDTO);
    return "user/detail";
  }

  @GetMapping("/user/member/update")
  public String updateForm(HttpSession session, Model model) {
    String myEmail = (String) session.getAttribute("loginEmail");
    MemberDTO memberDTO = memberService.updateForm(myEmail);
    model.addAttribute("updateMember", memberDTO);
    return "user/update";
  }

  @PostMapping("/user/member/update")
  public String update(@ModelAttribute MemberDTO memberDTO) {
    memberService.update(memberDTO);
    return "redirect:/user/member/" + memberDTO.getId();
  }

  @GetMapping("/user/member/delete/{id}")
  public String deleteById(@PathVariable Long id) {
    memberService.deleteById(id);
    return "redirect:/user/member/";
  }

  @GetMapping("/user/member/logout")
  public String logout(HttpSession session) {
    session.invalidate();
    return "index";
  }

  @PostMapping("/user/member/email-check")
  public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
    System.out.println("memberEmail = " + memberEmail);
    String checkResult = memberService.emailCheck(memberEmail);
    return checkResult;
//        if (checkResult != null) {
//            return "ok";
//        } else {
//            return "no";
//        }
  }

}