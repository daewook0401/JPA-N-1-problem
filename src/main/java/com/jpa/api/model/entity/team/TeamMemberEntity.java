package com.jpa.api.model.entity.team;

import com.jpa.api.model.entity.base.BaseEntity;
import com.jpa.api.model.entity.member.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "team_member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamMemberEntity extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private TeamEntity team;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;
    
    @Column(nullable = false)
    private String role;
    
    @Column(name = "is_team_lead", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isTeamLead;
}
