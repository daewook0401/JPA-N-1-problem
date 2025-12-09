package com.jpa.api.member.model.dto;

import com.jpa.api.member.model.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    private Long id;
    private Long teamId;
    private String teamName;
    private String username;
    private String email;
    private String name;
    private String phoneNumber;
    private String status;

    public static MemberDTO from(MemberEntity member) {
        return MemberDTO.builder()
                .id(member.getId())
                .teamId(member.getTeam() != null ? member.getTeam().getId() : null)
                .teamName(member.getTeam() != null ? member.getTeam().getTeamName() : null)
                .username(member.getUsername())
                .email(member.getEmail())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .status(member.getStatus().name())
                .build();
    }
}

