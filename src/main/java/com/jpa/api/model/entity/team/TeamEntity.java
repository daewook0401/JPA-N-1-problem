package com.jpa.api.model.entity.team;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpa.api.model.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_team")
public class TeamEntity extends BaseEntity {
    

    @Column(nullable = false, unique = true)
    private String teamName;
    
    @Column(nullable = false)
    private String department;
    
    @Column(length = 500)
    private String description;
}
