package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.CandidateFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateFileRepository extends JpaRepository<CandidateFile, Long> {
    @Query("SELECT a FROM CandidateFile a WHERE a.candidate.candidateId = :candidateId")
    CandidateFile findByCandidateId (Long candidateId);
}
