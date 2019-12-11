package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateVenueJobRepository extends JpaRepository<CandidateVenueJob, Long> {
    @Query("SELECT a FROM CandidateVenueJob a WHERE a.venueJob.venue.venueId =:venueId")
    List<CandidateVenueJob> findCandidatesByVenueId(long venueId);

    @Query("SELECT count(a) FROM CandidateVenueJob a WHERE a.venueJob.venue.venueId =:venueId")
    Integer findCountOfCandidatesByVenueId(long venueId);

    @Query("SELECT a FROM CandidateVenueJob a WHERE a.candidate.lastName LIKE CONCAT('%',:lastName,'%')")
    List<CandidateVenueJob> findByLastName(String lastName);

    @Query("SELECT a FROM CandidateVenueJob a WHERE a.venueJob.venue.venueId =:venueId ORDER BY a.candidate.lastName DESC")
    List<CandidateVenueJob> findCandidatesByVenueIdDESC(long venueId);

    @Query("SELECT a FROM CandidateVenueJob a WHERE a.venueJob.venue.venueId =:venueId ORDER BY a.candidate.lastName ASC")
    List<CandidateVenueJob> findCandidatesByVenueIdASC(long venueId);
}
