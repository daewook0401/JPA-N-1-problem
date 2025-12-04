package com.jpa.api.controller;

import com.jpa.api.model.entity.member.MemberEntity;
import com.jpa.api.model.entity.team.TeamEntity;
import com.jpa.api.model.service.JpaToH2DatabaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/jpa-to-h2")
@Slf4j
@RequiredArgsConstructor
public class JpaToH2DatabaseController {
    
    private final JpaToH2DatabaseService jpaToH2DatabaseService;
    
    // ============ 초기 데이터 생성 ============
    @PostMapping("/init")
    public String initializeData() {
        log.info("Initialize test data");
        jpaToH2DatabaseService.initializeTestData();
        return "Test data initialized successfully";
    }
    
    // ============ Member API ============
    @PostMapping("/members")
    public MemberEntity createMember(@RequestBody MemberEntity member) {
        log.info("Creating member: {}", member.getName());
        return jpaToH2DatabaseService.createMember(member);
    }
    
    @GetMapping("/members")
    public Page<MemberEntity> getMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        log.info("Fetching members - page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return jpaToH2DatabaseService.getAllMembers(pageable);
    }
    
    @GetMapping("/members/{id}")
    public MemberEntity getMemberById(@PathVariable Long id) {
        log.info("Fetching member by id: {}", id);
        return jpaToH2DatabaseService.getMemberById(id);
    }
    
    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable Long id) {
        log.info("Deleting member: {}", id);
        jpaToH2DatabaseService.deleteMember(id);
        return "Member deleted successfully";
    }
    
    // ============ Team API ============
    @PostMapping("/teams")
    public TeamEntity createTeam(@RequestBody TeamEntity team) {
        log.info("Creating team: {}", team.getTeamName());
        return jpaToH2DatabaseService.createTeam(team);
    }
    
    @GetMapping("/teams")
    public Page<TeamEntity> getTeams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        log.info("Fetching teams - page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return jpaToH2DatabaseService.getAllTeams(pageable);
    }
    
    @GetMapping("/teams/{id}")
    public TeamEntity getTeamById(@PathVariable Long id) {
        log.info("Fetching team by id: {}", id);
        return jpaToH2DatabaseService.getTeamById(id);
    }
    
    @DeleteMapping("/teams/{id}")
    public String deleteTeam(@PathVariable Long id) {
        log.info("Deleting team: {}", id);
        jpaToH2DatabaseService.deleteTeam(id);
        return "Team deleted successfully";
    }

}
