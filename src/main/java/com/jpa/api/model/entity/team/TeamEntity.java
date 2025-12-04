package com.jpa.api.model.entity.team;

import com.jpa.api.model.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_team")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamEntity extends BaseEntity {
    

    @Column(nullable = false, unique = true)
    private String teamName;
    
    @Column(nullable = false)
    private String department;
    
    @Column(length = 500)
    private String description;
}
