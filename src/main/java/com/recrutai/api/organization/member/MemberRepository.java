package com.recrutai.api.organization.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("""
            SELECT m FROM Member m
            JOIN FETCH m.user u
            LEFT JOIN FETCH u.location ul
            LEFT JOIN FETCH m.addedBy ab
            LEFT JOIN FETCH ab.user abu
            LEFT JOIN FETCH abu.location abul
            WHERE m.organization.id = :organizationId AND m.removedAt IS NULL
            """)
    List<Member> findAllByOrganizationId(@Param("organizationId") long organizationId);

    @Query(value = """
            SELECT EXISTS(
                SELECT 1 FROM tb_member
                WHERE user_id = :userId
                AND organization_id = :organizationId
                AND removed_at IS NULL
            )
            """, nativeQuery = true)
    boolean exists(@Param("organizationId") long organizationId, @Param("userId") long userId);

}
