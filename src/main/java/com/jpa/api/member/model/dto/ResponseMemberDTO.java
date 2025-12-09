package com.jpa.api.member.model.dto;

import org.springframework.data.domain.Page;

import com.jpa.api.member.model.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMemberDTO {
    private String message;
    private Page<MemberDTO> memberList;

    public static ResponseMemberDTO create(String message, Page<MemberEntity> members){
        Page<MemberDTO> dtoPage = members.map(MemberDTO::from);
        return ResponseMemberDTO.builder()
                .message(message)
                .memberList(dtoPage)
                .build();
    }
}
