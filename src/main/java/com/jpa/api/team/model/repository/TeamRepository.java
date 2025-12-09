package com.jpa.api.team.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpa.api.team.model.entity.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
    TeamEntity findByTeamName(String teamName);

    @Override
    @EntityGraph(attributePaths = {"members"})
    Page<TeamEntity> findAll(Pageable pageable);
}
