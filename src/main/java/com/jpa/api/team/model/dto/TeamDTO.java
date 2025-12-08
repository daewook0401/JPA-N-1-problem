package com.jpa.api.team.model.dto;

import com.jpa.api.team.model.entity.TeamEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDTO {

    private String teamName;
    private String description;

    public static TeamDTO from(TeamEntity team){
        return TeamDTO.builder()
                .teamName(team.getTeamName())
                .description(team.getDescription())
                .build();
    }
}
