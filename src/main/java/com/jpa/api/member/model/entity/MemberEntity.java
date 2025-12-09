package com.jpa.api.member.model.entity;

import com.jpa.api.global.entity.BaseEntity;
import com.jpa.api.member.model.dto.RequestMemberDTO;
import com.jpa.api.member.model.enums.MemberStatus;
import com.jpa.api.team.model.entity.TeamEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_member")
public class MemberEntity extends BaseEntity {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private TeamEntity team;

    @Column(nullable = false, unique = true, length = 30)
    private String username;
    
    @Column(nullable = false, unique = true, length = 50)
    private String email;
    
    @Column(nullable = false, length = 100)
    private String password;
    
    @Column(nullable = false, length = 50)
    private String name;
    
    @Column(name = "phone_number", unique = true, nullable = false, length = 20)    
    private String phoneNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberStatus status;

    public void update(RequestMemberDTO member){
        this.username = member.getUsername();
        this.email = member.getEmail();
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
    }

    public static MemberEntity create(RequestMemberDTO member, TeamEntity team){
        return MemberEntity.builder()
            .team(team)
            .username(member.getUsername())
            .email(member.getEmail())
            .password(member.getPassword())
            .name(member.getName())
            .phoneNumber(member.getPhoneNumber())
            .build();
    }
}
