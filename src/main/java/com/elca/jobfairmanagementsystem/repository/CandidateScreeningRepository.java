package com.elca.jobfairmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elca.jobfairmanagementsystem.entity.CandidateScreening;

/**
 *
 * @author ghr
 */
@Repository
public interface CandidateScreeningRepository extends JpaRepository<CandidateScreening, Long> {
}
