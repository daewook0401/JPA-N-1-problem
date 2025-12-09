package com.jpa.api.global.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.jpa.api.member.model.entity.MemberEntity;
import com.jpa.api.member.model.enums.MemberStatus;
import com.jpa.api.member.model.repository.MemberRepository;
import com.jpa.api.team.model.entity.TeamEntity;
import com.jpa.api.team.model.repository.TeamRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultInitializer implements ApplicationRunner {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Starting default data initialization...");
        
        // 팀 생성
        List<TeamEntity> teams = createTeams();
        
        // 팀별 멤버 생성
        createMembersForTeams(teams);
        
        log.info("Default data initialization completed!");
    }

    /**
     * 3개의 팀 생성
     */
    private List<TeamEntity> createTeams() {
        List<TeamEntity> teams = new ArrayList<>();
        
        String[] teamNames = {"개발팀", "마케팅팀", "운영팀"};
        String[] descriptions = {
            "백엔드 및 프론트엔드 개발을 담당하는 팀",
            "제품 마케팅 및 브랜드 관리를 담당하는 팀",
            "시스템 운영 및 서버 관리를 담당하는 팀"
        };
        
        for (int i = 0; i < teamNames.length; i++) {
            TeamEntity team = TeamEntity.builder()
                .teamName(teamNames[i])
                .description(descriptions[i])
                .build();
            teamRepository.save(team);
            teams.add(team);
            log.info("팀 생성: {}", teamNames[i]);
        }
        
        return teams;
    }

    /**
     * 각 팀별로 3명씩 멤버 생성
     */
    private void createMembersForTeams(List<TeamEntity> teams) {
        String[] memberNames = {"김철수", "이영희", "박민수", "최동욱", "정수진", "조혜미", "윤재훈", "남궁지현", "한지훈"};
        String[] usernames = {"chulsu.kim", "younghee.lee", "minsu.park", "dongwook.choi", "sujin.jung", "hyemi.jo", "jaehun.yoon", "juhyeon.namgoong", "jihun.han"};
        
        int memberIndex = 0;
        for (int teamIdx = 0; teamIdx < teams.size(); teamIdx++) {
            TeamEntity team = teams.get(teamIdx);
            
            for (int i = 0; i < 3; i++) {
                String username = usernames[memberIndex];
                String email = username + "@company.com";
                
                MemberEntity member = MemberEntity.builder()
                    .team(team)
                    .username(username)
                    .email(email)
                    .password("password" + (memberIndex + 1))
                    .name(memberNames[memberIndex])
                    .phoneNumber("010-" + String.format("%04d", memberIndex + 1) + "-0000")
                    .status(MemberStatus.ACTIVE)
                    .build();
                
                memberRepository.save(member);
                log.info("{} - 멤버 생성: {} ({})", team.getTeamName(), memberNames[memberIndex], username);
                memberIndex++;
            }
        }
    }
}
