package com.jpa.api.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.api.member.model.dto.RequestMemberDTO;
import com.jpa.api.member.model.dto.ResponseMemberDTO;
import com.jpa.api.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> createMember(@Valid @RequestBody RequestMemberDTO member, @RequestBody Long teamId) {
        memberService.createMember(member, teamId);
        return ResponseEntity.ok("Create member success");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, @RequestBody RequestMemberDTO member) {
        memberService.updateMember(id, member);
        return ResponseEntity.ok("Update member success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.ok("delete member success");
    }

    @GetMapping
    public ResponseEntity<ResponseMemberDTO> selectAllMember(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "100") int size,
        @RequestHeader(value = "Accept", required = false) String acceptHeader
    ) {
        log.info("Accept Header = {}", acceptHeader);
        Pageable pageable = PageRequest.of(page, size);
        ResponseMemberDTO members = memberService.selectAllMembers(pageable);
        return ResponseEntity.ok(members);
    }
}
