package com.jpa.api.team.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.api.team.model.dto.RequestTeamDTO;
import com.jpa.api.team.model.dto.ResponseTeamDTO;
import com.jpa.api.team.model.service.TeamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<String> createTeam(@Valid @RequestBody RequestTeamDTO team) {
        teamService.createTeam(team);
        return ResponseEntity.ok("Create team success");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable Long id, @RequestBody RequestTeamDTO team) {
        teamService.updateTeam(id, team);
        return ResponseEntity.ok("Update team success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok("Delete team success");
    }

    @GetMapping
    public ResponseEntity<ResponseTeamDTO> selectAllTeams(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        ResponseTeamDTO teams = teamService.selectAllTeams(pageable);
        return ResponseEntity.ok(teams);
    }
}
