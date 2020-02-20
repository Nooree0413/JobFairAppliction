package com.elca.jobfairmanagementsystem.repository;

import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateVenueJobRepository extends JpaRepository<CandidateVenueJob, Long>, QuerydslPredicateExecutor<CandidateVenueJob> {
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
    Page<CandidateVenueJob> findAllCandidatesByASC(Pageable pageable);

    @Query("SELECT a FROM CandidateVenueJob a WHERE a.candidate.currentLevel =:currentLevel")
    Page<CandidateVenueJob> findByCurrentLevel(String currentLevel, Pageable pageable);

    @Query("SELECT a FROM CandidateVenueJob a JOIN a.candidate c JOIN c.candidateScreenings cs WHERE cs.screeningStatus =:screeningStatus")
    Page<CandidateVenueJob> findByScreeningStatus(String screeningStatus, Pageable pageable);

    @Query("SELECT a FROM CandidateVenueJob a ORDER BY a.candidate.registrationDate DESC")
    Page<CandidateVenueJob> findAllCandidateVenueJobOrderByRegistrationDate(Pageable pageable);

    @Query("SELECT count(a) FROM CandidateVenueJob a JOIN a.venueJob vj JOIN a.candidate c JOIN vj.venue v JOIN c.candidateScreenings cs WHERE v.venueId=:venueId AND cs.screeningStatus =:screeningStatus")
    Integer findCountOfScreeningStatusByVenueId(long venueId, String screeningStatus);

    @Query("SELECT count(a) FROM CandidateVenueJob a JOIN a.venueJob vj JOIN vj.venue v JOIN a.candidate c WHERE v.venueId =:venueId AND MONTH(c.availabilityDate) = :month")
    Integer findCandidatesPerMonthByVenue(long venueId, Integer month);

    @Query("SELECT count(a) FROM CandidateVenueJob a")
    Integer findCountOfCandidates();

    @Query("SELECT count(a) FROM CandidateVenueJob a JOIN a.venueJob vj JOIN a.candidate c JOIN vj.venue v JOIN c.candidateScreenings cs WHERE cs.screeningStatus =:screeningStatus")
    Integer findCountOfScreeningStatusByAllVenue(String screeningStatus);

    @Query("SELECT count(a) FROM CandidateVenueJob a WHERE a.venueJob.job.category =:category")
    Integer findCountOfCandidatesPerJobCategoryByAllVenue(String category);

    @Query("SELECT count(a) FROM CandidateVenueJob a WHERE a.venueJob.job.category =:category AND a.venueJob.venue.venueId =:venueId")
    Integer findCountOfCandidatesPerJobCategoryByVenue(long venueId, String category);

    @Query("SELECT count(a) FROM CandidateVenueJob a JOIN a.venueJob vj JOIN vj.venue v JOIN a.candidate c WHERE MONTH(c.availabilityDate) = :month")
    Integer findCandidatesPerMonthByAllVenue(Integer month);

    Page<CandidateVenueJob> findAll(Pageable pageable);
}
