package com.jpa.api.model.repository;

import com.jpa.api.model.entity.member.MemberEntity;
import com.jpa.api.model.entity.team.TeamEntity;
import com.jpa.api.model.entity.team.TeamMemberEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMemberEntity, Long> {
    void deleteByMember(MemberEntity member);
}
