package com.jpa.api.model.service;

import com.jpa.api.model.entity.member.MemberEntity;
import com.jpa.api.model.entity.team.TeamEntity;
import com.jpa.api.model.entity.team.TeamMemberEntity;
import com.jpa.api.model.repository.MemberRepository;
import com.jpa.api.model.repository.TeamMemberRepository;
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
    private final TeamMemberRepository teamMemberRepository;
    
    // ============ Member 관련 메서드 ============
    @Override
    public MemberEntity createMember(MemberEntity member) {
        log.info("Creating member: {}", member.getUsername());
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
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        teamMemberRepository.deleteByMember(member);
        memberRepository.deleteById(id);
    }

    @Override
    public MemberEntity updateMember(MemberEntity member){
        log.info("Updating member: {}", member.getId());
        MemberEntity existing = memberRepository.findById(member.getId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        
        if (member.getEmail() != null && !member.getEmail().isEmpty()) 
            existing.setEmail(member.getEmail());
        if (member.getName() != null && !member.getName().isEmpty()) 
            existing.setName(member.getName());
        if (member.getPassword() != null && !member.getPassword().isEmpty()) 
            existing.setPassword(member.getPassword());
        if (member.getPhoneNumber() != null && !member.getPhoneNumber().isEmpty()) 
            existing.setPhoneNumber(member.getPhoneNumber());
        if (member.getStatus() != null) 
            existing.setStatus(member.getStatus());
        
        return memberRepository.save(existing);
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

    @Override
    public TeamMemberEntity createTeamMember(TeamMemberEntity teamMember){
        log.info("Creating teamMember: {}", teamMember.getTeam());
        return teamMemberRepository.save(teamMember);

    }
    
    @Override
    public Page<TeamMemberEntity> getAllTeamMember(Pageable pageable){
        log.info("Fetching teamMembers with pagination: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return teamMemberRepository.findAll(pageable);
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

    @Override
    public void createDummyMembers(int count) {
        log.info("Creating {} dummy members...", count);
        String[] roles = {"ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER"};
        
        for (int i = 1; i <= count; i++) {
            MemberEntity member = MemberEntity.builder()
                    .username("user" + i)
                    .email("user" + i + "@example.com")
                    .password("password" + i)
                    .name("사용자" + i)
                    .phoneNumber("010-" + String.format("%04d", i) + "-" + String.format("%04d", i))
                    .status(com.jpa.api.model.enums.MemberStatus.ACTIVE)
                    .build();
            memberRepository.save(member);
        }
        log.info("{} dummy members created successfully", count);
    }

    @Override
    public void createDummyTeamMembers(int count) {
        log.info("Creating {} dummy team members...", count);
        String[] roles = {"DEVELOPER", "DESIGNER", "MANAGER"};
        
        var teams = teamRepository.findAll();
        var members = memberRepository.findAll();
        
        if (teams.isEmpty() || members.isEmpty()) {
            log.warn("Teams or Members not found");
            return;
        }
        
        int memberIdx = 0;
        for (int i = 0; i < count; i++) {
            TeamMemberEntity teamMember = TeamMemberEntity.builder()
                    .team(teams.get(i % teams.size()))
                    .member(members.get(memberIdx % members.size()))
                    .role(roles[i % roles.length])
                    .isTeamLead(i % 20 == 0)
                    .build();
            teamMemberRepository.save(teamMember);
            memberIdx++;
        }
        log.info("{} dummy team members created successfully", count);
    }
}
