package com.min.fashion.member.controller;


import com.min.fashion.member.dto.MemberDTO;
import com.min.fashion.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;

  @PostMapping("/register")
  public ResponseEntity<MemberDTO> register(@RequestBody MemberDTO memberDTO) {
    memberService.save(memberDTO);
    return new ResponseEntity<>(memberDTO, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<MemberDTO> login(@RequestBody MemberDTO memberDTO) {
    MemberDTO loginResult = memberService.login(memberDTO);
    if (loginResult != null) {
      return ResponseEntity.ok(loginResult);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<MemberDTO>> findAll() {
    List<MemberDTO> members = memberService.findAll();
    return ResponseEntity.ok(members);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberDTO> findById(@PathVariable Long id) {
    MemberDTO member = memberService.findById(id);
    if (member != null) {
      return ResponseEntity.ok(member);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<MemberDTO> update(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
    memberService.update(id, memberDTO);
    MemberDTO updatedMember = memberService.findById(id);
    if (updatedMember != null) {
      return ResponseEntity.ok(updatedMember);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    memberService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout() {
    // 토큰 기반 인증 시스템에서는 클라이언트 측에서 토큰을 삭제해야 합니다.
    return ResponseEntity.ok().build();
  }

  @PostMapping("/email-check")
  public ResponseEntity<String> emailCheck(@RequestParam("memberEmail") String memberEmail) {
    boolean isEmailAvailable = memberService.emailCheck(memberEmail);
    return ResponseEntity.ok(isEmailAvailable ? "Available" : "Not Available");
  }
}
