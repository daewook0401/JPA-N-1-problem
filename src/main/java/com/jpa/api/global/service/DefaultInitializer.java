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
     * 여러 팀 생성
     */
    private List<TeamEntity> createTeams() {
        List<TeamEntity> teams = new ArrayList<>();
        
        String[] teamNames = {"개발팀", "마케팅팀", "운영팀", "HR팀", "영업팀", "디자인팀", "QA팀", "재무팀", "법무팀", "연구팀"};
        String[] descriptions = {
            "백엔드 및 프론트엔드 개발을 담당하는 팀",
            "제품 마케팅 및 브랜드 관리를 담당하는 팀",
            "시스템 운영 및 서버 관리를 담당하는 팀",
            "인사 관리 및 채용을 담당하는 팀",
            "영업 및 고객 관리를 담당하는 팀",
            "UI/UX 디자인을 담당하는 팀",
            "품질 보증 및 테스트를 담당하는 팀",
            "재무 및 회계 관리를 담당하는 팀",
            "법무 및 계약 관리를 담당하는 팀",
            "연구 및 개발을 담당하는 팀"
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
     * 각 팀별로 100명씩 멤버 생성
     */
    private void createMembersForTeams(List<TeamEntity> teams) {
        String[] firstNames = {"김", "이", "박", "최", "정", "조", "윤", "남궁", "한", "송", "강", "홍", "오", "서", "신"};
        String[] lastNames = {"철수", "영희", "민수", "동욱", "수진", "혜미", "재훈", "지현", "지훈", "민준", "서연", "도윤", "지우", "예준", "채원"};
        
        for (int teamIdx = 0; teamIdx < teams.size(); teamIdx++) {
            TeamEntity team = teams.get(teamIdx);
            
            for (int i = 1; i <= 100; i++) {
                String firstName = firstNames[(teamIdx * 100 + i) % firstNames.length];
                String lastName = lastNames[i % lastNames.length];
                String fullName = firstName + lastName;
                String username = "user" + teamIdx + "_" + i;
                String email = username + "@company.com";
                
                MemberEntity member = MemberEntity.builder()
                    .team(team)
                    .username(username)
                    .email(email)
                    .password("password" + i)
                    .name(fullName)
                    .phoneNumber("010-" + String.format("%04d", (teamIdx * 100 + i)) + "-0000")
                    .status(MemberStatus.ACTIVE)
                    .build();
                
                memberRepository.save(member);
                if (i % 10 == 0) {  // 10명마다 로그 출력
                    log.info("{} - 멤버 생성 진행: {}명 완료", team.getTeamName(), i);
                }
            }
            log.info("{} - 총 100명 멤버 생성 완료", team.getTeamName());
        }
    }
}
