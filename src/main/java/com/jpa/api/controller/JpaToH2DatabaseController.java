package com.jpa.api.controller;

import com.jpa.api.model.entity.member.MemberEntity;
import com.jpa.api.model.entity.team.TeamEntity;
import com.jpa.api.model.entity.team.TeamMemberEntity;
import com.jpa.api.model.service.JpaToH2DatabaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/jpa-to-h2")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class JpaToH2DatabaseController {
    
    private final JpaToH2DatabaseService jpaToH2DatabaseService;
    
    // ============ 초기 데이터 생성 ============
    @PostMapping("/init")
    public ResponseEntity<?> initializeData() {
        log.info("Initialize test data");
        try {
            jpaToH2DatabaseService.initializeTestData();
            return ResponseEntity.ok(createResponse("success", "Test data initialized successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }
    
    // ============ Member API ============
    @PostMapping("/members")
    public ResponseEntity<?> createMember(@RequestBody MemberEntity member) {
        log.info("Creating member: {}", member.getName());
        try {
            MemberEntity created = jpaToH2DatabaseService.createMember(member);
            return ResponseEntity.ok(createResponse("success", "Member created successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }
    
    @GetMapping("/members")
    public ResponseEntity<?> getMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        log.info("Fetching members - page: {}, size: {}", page, size);
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<MemberEntity> members = jpaToH2DatabaseService.getAllMembers(pageable);
            return ResponseEntity.ok(createResponse("success", "Members fetched successfully", members));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }
    
    @GetMapping("/members/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        log.info("Fetching member by id: {}", id);
        try {
            MemberEntity member = jpaToH2DatabaseService.getMemberById(id);
            return ResponseEntity.ok(createResponse("success", "Member fetched successfully", member));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @RequestBody MemberEntity member) {
        log.info("Updating member info by id: {}", id);
        try {
            MemberEntity updated = jpaToH2DatabaseService.updateMember(member);
            return ResponseEntity.ok(createResponse("success", "Member updated successfully", updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }
    
    @DeleteMapping("/members/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        log.info("Deleting member: {}", id);
        try {
            jpaToH2DatabaseService.deleteMember(id);
            return ResponseEntity.ok(createResponse("success", "Member deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }
    
    // ============ Team API ============
    @PostMapping("/teams")
    public ResponseEntity<?> createTeam(@RequestBody TeamEntity team) {
        log.info("Creating team: {}", team.getTeamName());
        try {
            TeamEntity created = jpaToH2DatabaseService.createTeam(team);
            return ResponseEntity.ok(createResponse("success", "Team created successfully", created));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }
    
    @GetMapping("/teams")
    public ResponseEntity<?> getTeams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {
        log.info("Fetching teams - page: {}, size: {}", page, size);
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<TeamEntity> teams = jpaToH2DatabaseService.getAllTeams(pageable);
            return ResponseEntity.ok(createResponse("success", "Teams fetched successfully", teams));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }
    
    @GetMapping("/teams/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable Long id) {
        log.info("Fetching team by id: {}", id);
        try {
            TeamEntity team = jpaToH2DatabaseService.getTeamById(id);
            return ResponseEntity.ok(createResponse("success", "Team fetched successfully", team));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }
    
    @DeleteMapping("/teams/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        log.info("Deleting team: {}", id);
        try {
            jpaToH2DatabaseService.deleteTeam(id);
            return ResponseEntity.ok(createResponse("success", "Team deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }

    @GetMapping("/teams/member")
    public ResponseEntity<?> getTeamMember(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size) {
        log.info("getTeamMembers - page: {}, size: {}", page, size);
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<TeamMemberEntity> members = jpaToH2DatabaseService.getAllTeamMember(pageable);
            return ResponseEntity.ok(createResponse("success", "Team members fetched successfully", members));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }


    
    // ============ 더미 데이터 생성 ============
    @PostMapping("/dummy-members/{count}")
    public ResponseEntity<?> createDummyMembers(@PathVariable int count) {
        log.info("Creating {} dummy members", count);
        try {
            jpaToH2DatabaseService.createDummyMembers(count);
            return ResponseEntity.ok(createResponse("success", count + " dummy members created successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }

    @PostMapping("/dummy-team-members/{count}")
    public ResponseEntity<?> createDummyTeamMembers(@PathVariable int count) {
        log.info("Creating {} dummy team members", count);
        try {
            jpaToH2DatabaseService.createDummyTeamMembers(count);
            return ResponseEntity.ok(createResponse("success", count + " dummy team members created successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createResponse("error", e.getMessage(), null));
        }
    }

    // ============ 응답 포맷 유틸리티 ============
    private Map<String, Object> createResponse(String status, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        response.put("data", data);
        return response;
    }

}
