package com.jpa.api.team.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpa.api.global.entity.BaseEntity;
import com.jpa.api.team.model.dto.RequestTeamDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_team")
public class TeamEntity extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String teamName;
    
    @Column(length = 500)
    private String description;

    public void update(RequestTeamDTO team){
        this.teamName = team.getTeamName();
        this.description = team.getDescription();
    }

    public static TeamEntity create(RequestTeamDTO team){
        return TeamEntity.builder()
                .teamName(team.getTeamName())
                .description(team.getDescription())
                .build();
    }
}
