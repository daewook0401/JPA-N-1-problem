package com.jpa.api.team.model.dto;

import org.springframework.data.domain.Page;

import com.jpa.api.team.model.entity.TeamEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTeamDTO {
    private String message;
    private Page<TeamDTO> teamList;

    public static ResponseTeamDTO create(String message, Page<TeamEntity> teams) {
        Page<TeamDTO> dtoPage = teams.map(TeamDTO::from);
        return ResponseTeamDTO.builder()
                .message(message)
                .teamList(dtoPage)
                .build();
    }
}
