package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.CandidateScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ghr
 */
@Repository
public interface CandidateScreeningRepository extends JpaRepository<CandidateScreening, Long> {
}
