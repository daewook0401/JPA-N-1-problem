package com.jpa.api.team.model.service;

import org.springframework.data.domain.Pageable;

import com.jpa.api.team.model.dto.RequestTeamDTO;
import com.jpa.api.team.model.dto.ResponseTeamDTO;

public interface TeamService {
    
    void createTeam(RequestTeamDTO team);
    
    void updateTeam(Long id, RequestTeamDTO team);
    
    void deleteTeam(Long id);
    
    ResponseTeamDTO selectAllTeams(Pageable pageable);
}
