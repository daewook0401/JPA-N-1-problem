package com.jpa.api.member.model.service;

import org.springframework.data.domain.Pageable;

import com.jpa.api.member.model.dto.RequestMemberDTO;
import com.jpa.api.member.model.dto.ResponseMemberDTO;

public interface MemberService {

    void createMember(RequestMemberDTO member);
    void updateMember(Long id, RequestMemberDTO member);
    void deleteMember(Long id);
    ResponseMemberDTO selectAllMembers(Pageable pageable);
}
