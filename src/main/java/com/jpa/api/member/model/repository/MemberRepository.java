package com.jpa.api.member.model.repository;

import com.jpa.api.member.model.entity.MemberEntity;
import com.jpa.api.member.model.enums.MemberStatus;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username);
    @Query("""
        SELECT m FROM MemberEntity m
        WHERE (:keyword IS NULL OR :keyword = '' OR
            LOWER(m.username) = LOWER(:keyword) OR
            LOWER(m.email) = LOWER(:keyword) OR
            LOWER(m.name) = LOWER(:keyword) OR
            LOWER(m.phoneNumber) = LOWER(:keyword))
        AND (:status IS NULL OR m.status IN :status)
    """)
    Page<MemberEntity> searchMembers(
            @Param("keyword") String keyword,
            @Param("status") java.util.List<MemberStatus> status,
            Pageable pageable);
}
