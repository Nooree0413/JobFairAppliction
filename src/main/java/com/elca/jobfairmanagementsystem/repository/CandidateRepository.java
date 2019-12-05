package com.elca.jobfairmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elca.jobfairmanagementsystem.entity.Candidate;

/**
 *
 * @author ghr
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByEmail (String email);
}
