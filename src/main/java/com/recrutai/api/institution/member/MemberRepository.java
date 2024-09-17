package com.recrutai.api.institution.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByInstitutionId(Long institutionId);

    @Query(value = """
            SELECT EXISTS(
                SELECT 1 FROM tb_member
                WHERE user_id = :userId
                AND institution_id = :institutionId
                AND removed_at IS NULL
            )
            """, nativeQuery = true)
    boolean exists(long institutionId, long userId);

}
