package com.elca.jobfairmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elca.jobfairmanagementsystem.entity.Candidate;

import java.util.List;

/**
 *
 * @author ghr
 */
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT c FROM Candidate c INNER JOIN c.candidateVenueJobs cvj INNER JOIN cvj.venueJob vj WHERE vj.venue.venueId =:venueId")
    List<Candidate> findCandidatesByVenueId(long venueId);
}
