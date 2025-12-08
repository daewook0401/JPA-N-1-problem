package com.jpa.api.team.model.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jpa.api.global.exception.util.TeamNotFoundException;
import com.jpa.api.team.model.dto.RequestTeamDTO;
import com.jpa.api.team.model.dto.ResponseTeamDTO;
import com.jpa.api.team.model.entity.TeamEntity;
import com.jpa.api.team.model.repository.TeamRepository;
import com.jpa.api.team.model.service.TeamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public void createTeam(RequestTeamDTO team) {
        teamRepository.save(TeamEntity.create(team));
    }

    @Override
    public void updateTeam(Long id, RequestTeamDTO team) {
        TeamEntity teamEntity = teamRepository.findById(id)
            .orElseThrow(() -> new TeamNotFoundException("잘못된 수정 요청"));
        teamEntity.update(team);
    }

    @Override
    public void deleteTeam(Long id) {
        TeamEntity teamEntity = teamRepository.findById(id)
            .orElseThrow(() -> new TeamNotFoundException("잘못된 삭제 요청"));
        teamRepository.deleteById(teamEntity.getId());
    }

    @Override
    public ResponseTeamDTO selectAllTeams(Pageable pageable) {
        Page<TeamEntity> teamList = teamRepository.findAll(pageable);
        if (teamList.equals(null)){
            throw new TeamNotFoundException("조회에 실패하였습니다.");
        }
        return ResponseTeamDTO.create("조회에 성공하였습니다.", teamList);
    }

}
