package com.jpa.api.model.service;

import com.jpa.api.model.entity.member.MemberEntity;
import com.jpa.api.model.entity.team.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaToH2DatabaseService {
    
    // Member 관련
    MemberEntity createMember(MemberEntity member);
    Page<MemberEntity> getAllMembers(Pageable pageable);
    MemberEntity getMemberById(Long id);
    void deleteMember(Long id);
    
    // Team 관련
    TeamEntity createTeam(TeamEntity team);
    Page<TeamEntity> getAllTeams(Pageable pageable);
    TeamEntity getTeamById(Long id);
    void deleteTeam(Long id);
    
    // 초기 데이터 생성
    void initializeTestData();
}
