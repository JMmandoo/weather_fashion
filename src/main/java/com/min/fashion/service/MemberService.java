package com.min.fashion.service;

import com.min.fashion.dto.MemberDTO;
import com.min.fashion.entity.MemberEntity;
import com.min.fashion.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  public void save(MemberDTO memberDTO) {
    // dto -> entity 변환
    // repository의 save 메서드 호출
    MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
    memberRepository.save(memberEntity);
    // repository의 save메서드 호출 (조건, entity객체를 넘겨줘야함)
  }
}
