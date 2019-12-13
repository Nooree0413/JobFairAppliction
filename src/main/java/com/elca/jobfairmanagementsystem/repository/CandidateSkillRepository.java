package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.CandidateSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill , Long> {
    @Query("SELECT a FROM CandidateSkill a WHERE a.candidate.candidateId =:candidateId")
    List<CandidateSkill> findByCandidateId(long candidateId);
}
