package com.min.fashion.member.service;

import com.min.fashion.member.dto.MemberDTO;
import com.min.fashion.member.entity.MemberEntity;
import com.min.fashion.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  public void save(MemberDTO memberDTO) {
    MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
    memberRepository.save(memberEntity);
  }

  public MemberDTO login(MemberDTO memberDTO) {
    Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
    if (byMemberEmail.isPresent()) {
      MemberEntity memberEntity = byMemberEmail.get();
      if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
        return MemberDTO.toMemberDTO(memberEntity);
      }
    }
    return null;
  }

  public List<MemberDTO> findAll() {
    List<MemberEntity> memberEntityList = memberRepository.findAll();
    List<MemberDTO> memberDTOList = new ArrayList<>();
    for (MemberEntity memberEntity : memberEntityList) {
      memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
    }
    return memberDTOList;
  }

  public MemberDTO findById(Long id) {
    return memberRepository.findById(id)
        .map(MemberDTO::toMemberDTO)
        .orElse(null);
  }

  public MemberDTO updateForm(String myEmail) {
    return memberRepository.findByMemberEmail(myEmail)
        .map(MemberDTO::toMemberDTO)
        .orElse(null);
  }

  public void update(Long id, MemberDTO memberDTO) {
    Optional<MemberEntity> existingMember = memberRepository.findById(id);
    if (existingMember.isPresent()) {
      MemberEntity memberEntity = existingMember.get();
      // 여기에서 memberEntity의 필드를 memberDTO의 값으로 업데이트
      memberEntity.setMemberEmail(memberDTO.getMemberEmail());
      memberEntity.setMemberPassword(memberDTO.getMemberPassword());
      memberEntity.setMemberName(memberDTO.getMemberName());
      // 업데이트된 entity 저장
      memberRepository.save(memberEntity);
    }
  }

  public void deleteById(Long id) {
    memberRepository.deleteById(id);
  }

  public boolean emailCheck(String memberEmail) {
    return memberRepository.findByMemberEmail(memberEmail).isEmpty();
  }
}
