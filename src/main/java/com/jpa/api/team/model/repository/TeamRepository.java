package com.jpa.api.team.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.api.team.model.entity.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    TeamEntity findByTeamName(String teamName);
    
    @Query("SELECT t FROM TeamEntity t WHERE " +
            "(:keyword IS NULL OR :keyword = '' OR " +
            "LOWER(t.teamName) = LOWER(:keyword) OR " +
            "LOWER(t.description) = LOWER(:keyword) OR " +
            "CAST(t.id AS STRING) = :keyword)")
    Page<TeamEntity> searchTeams(
            @Param("keyword") String keyword,
            Pageable pageable);
}
