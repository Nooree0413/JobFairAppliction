package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateVenueJobRepository extends JpaRepository<CandidateVenueJob, Long> {
}
