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
    // 팀에 포함된 멤버 수 (N+1 확인용)
    private int memberCount;

    public static TeamDTO from(TeamEntity team){
        return TeamDTO.builder()
                .teamName(team.getTeamName())
                .description(team.getDescription())
                // 여기서 team.getMembers() 접근 시, LAZY 로딩으로 인해 팀 수만큼 추가 쿼리가 나가며 N+1 상황이 발생
                .memberCount(team.getMembers() != null ? team.getMembers().size() : 0)
                .build();
    }
}
