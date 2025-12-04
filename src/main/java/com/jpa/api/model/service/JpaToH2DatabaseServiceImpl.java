package com.jpa.api.model.service;

import com.jpa.api.model.entity.member.MemberEntity;
import com.jpa.api.model.entity.team.TeamEntity;
import com.jpa.api.model.repository.MemberRepository;
import com.jpa.api.model.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class JpaToH2DatabaseServiceImpl implements JpaToH2DatabaseService {
    
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    
    // ============ Member 관련 메서드 ============
    @Override
    public MemberEntity createMember(MemberEntity member) {
        log.info("Creating member: {}", member.getName());
        return memberRepository.save(member);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<MemberEntity> getAllMembers(Pageable pageable) {
        log.info("Fetching members with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return memberRepository.findAll(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public MemberEntity getMemberById(Long id) {
        log.info("Fetching member by id: {}", id);
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
    
    @Override
    public void deleteMember(Long id) {
        log.info("Deleting member: {}", id);
        memberRepository.deleteById(id);
    }
    
    // ============ Team 관련 메서드 ============
    @Override
    public TeamEntity createTeam(TeamEntity team) {
        log.info("Creating team: {}", team.getTeamName());
        return teamRepository.save(team);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<TeamEntity> getAllTeams(Pageable pageable) {
        log.info("Fetching teams with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return teamRepository.findAll(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public TeamEntity getTeamById(Long id) {
        log.info("Fetching team by id: {}", id);
        return teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found"));
    }
    
    @Override
    public void deleteTeam(Long id) {
        log.info("Deleting team: {}", id);
        teamRepository.deleteById(id);
    }
    
    
    // ============ 초기 데이터 생성 ============
    @Override
    public void initializeTestData() {
        log.info("Initializing test data...");
        
        // 3개의 팀 생성
        TeamEntity devTeam = TeamEntity.builder()
                .teamName("개발팀")
                .department("Development")
                .description("백엔드, 프론트엔드 개발을 담당하는 팀")
                .build();
        
        TeamEntity marketingTeam = TeamEntity.builder()
                .teamName("마케팅팀")
                .department("Marketing")
                .description("제품 마케팅 및 홍보를 담당하는 팀")
                .build();

        TeamEntity operationTeam = TeamEntity.builder()
                .teamName("운영팀")
                .department("Operations")
                .description("시스템 운영 및 서버 관리를 담당하는 팀")
                .build();
                
        teamRepository.save(devTeam);
        teamRepository.save(marketingTeam);
        teamRepository.save(operationTeam);
        
        log.info("Teams created successfully");
    }
}
