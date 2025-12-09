package com.jpa.api.member.model.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.api.global.exception.util.MemberNotFoundException;
import com.jpa.api.member.model.dto.RequestMemberDTO;
import com.jpa.api.member.model.dto.ResponseMemberDTO;
import com.jpa.api.member.model.entity.MemberEntity;
import com.jpa.api.member.model.repository.MemberRepository;
import com.jpa.api.member.model.service.MemberService;
import com.jpa.api.team.model.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void createMember(RequestMemberDTO member, Long teamId) {
        memberRepository.save(MemberEntity.create(member, teamRepository.findById(teamId).get()));
    }

    @Override
    @Transactional
    public void updateMember(Long id, RequestMemberDTO member) {
        MemberEntity memberEntity = memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException("잘못된 수정 요청"));
        
        memberEntity.update(member);
    }

    @Override
    @Transactional
    public void deleteMember(Long id) {
        MemberEntity memberEntity = memberRepository.findById(id)
            .orElseThrow(() -> new MemberNotFoundException("잘못된 삭제 요청"));

        memberRepository.deleteById(memberEntity.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseMemberDTO selectAllMembers(Pageable pageable) {
        Page<MemberEntity> memberList = memberRepository.findAll(pageable);
        if (memberList.equals(null)){
            throw new MemberNotFoundException("조회에 실패하였습니다.");
        }
        return ResponseMemberDTO.create("조회에 성공하였습니다.", memberList);
    }

}
