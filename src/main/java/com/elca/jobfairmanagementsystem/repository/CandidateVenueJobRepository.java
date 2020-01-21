package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateVenueJobRepository extends JpaRepository<CandidateVenueJob, Long> {
    @Query("SELECT a FROM CandidateVenueJob a WHERE a.venueJob.venue.venueId =:venueId ORDER BY a.candidate.registrationDate DESC")
    Page<CandidateVenueJob> findCandidatesByVenueId(long venueId, Pageable pageable);

    @Query("SELECT count(a) FROM CandidateVenueJob a WHERE a.venueJob.venue.venueId =:venueId")
    Integer findCountOfCandidatesByVenueId(long venueId);

    @Query("SELECT a FROM CandidateVenueJob a WHERE a.candidate.lastName LIKE CONCAT('%',:lastName,'%')")
    List<CandidateVenueJob> findByLastName(String lastName);

    @Query("SELECT a FROM CandidateVenueJob a WHERE a.venueJob.venue.venueId =:venueId ORDER BY a.candidate.lastName DESC")
    List<CandidateVenueJob> findCandidatesByVenueIdDESC(long venueId);

    @Query("SELECT a FROM CandidateVenueJob a WHERE a.venueJob.venue.venueId =:venueId ORDER BY a.candidate.lastName ASC")
    List<CandidateVenueJob> findCandidatesByVenueIdASC(long venueId);

    @Query("SELECT a FROM CandidateVenueJob a ORDER BY a.candidate.lastName DESC")
    Page<CandidateVenueJob> findAllCandidatesByDESC(Pageable pageable);

    @Query("SELECT a FROM CandidateVenueJob a ORDER BY a.candidate.lastName ASC")
    Page<CandidateVenueJob> findAllCandidatesBydASC(Pageable pageable);

    @Query("SELECT a FROM CandidateVenueJob a WHERE a.candidate.currentLevel =:currentLevel")
    Page<CandidateVenueJob> findByCurrentLevel(String currentLevel,Pageable pageable);
}
