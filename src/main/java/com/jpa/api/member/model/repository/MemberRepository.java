package com.jpa.api.member.model.repository;

import com.jpa.api.member.model.entity.MemberEntity;
import com.jpa.api.member.model.enums.MemberStatus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username);

    @Override
    @EntityGraph(attributePaths = {"team"})
    Page<MemberEntity> findAll(Pageable pageable);
}
