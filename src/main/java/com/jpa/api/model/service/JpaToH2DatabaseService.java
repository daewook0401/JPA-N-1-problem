package com.jpa.api.model.service;

import com.jpa.api.model.entity.member.MemberEntity;
import com.jpa.api.model.entity.team.TeamEntity;
import com.jpa.api.model.entity.team.TeamMemberEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JpaToH2DatabaseService {
    
    // Member 관련
    MemberEntity createMember(MemberEntity member);
    Page<MemberEntity> getAllMembers(Pageable pageable);
    MemberEntity getMemberById(Long id);
    void deleteMember(Long id);
    MemberEntity updateMember(MemberEntity member);

    // Team 관련
    TeamEntity createTeam(TeamEntity team);
    Page<TeamEntity> getAllTeams(Pageable pageable);
    TeamEntity getTeamById(Long id);
    void deleteTeam(Long id);
    
    // TeamMember 관련
    TeamMemberEntity createTeamMember (TeamMemberEntity teamMember);
    Page<TeamMemberEntity> getAllTeamMember(Pageable pageable);

    // 초기 데이터 생성
    void initializeTestData();
    void createDummyMembers(int count);
    void createDummyTeamMembers(int count);
}
