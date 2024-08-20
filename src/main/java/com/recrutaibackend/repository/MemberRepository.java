package com.recrutaibackend.repository;

import com.recrutaibackend.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByInstitutionId(Long institutionId);

}
