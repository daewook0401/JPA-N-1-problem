package com.jpa.api.team.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTeamDTO {
    
    @NotBlank(message = "팀명은 필수입니다.")
    private String teamName;
    
    private String description;
}
