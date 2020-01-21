package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobCountAllDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobPaginationDto;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CandidateVenueJobService {
    CandidateVenueJobPaginationDto findAllCandidateVenueJobs(Pageable pageable) throws CandidateVenueJobNotFoundException;

    void deleteCandidateVenueJob(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException;

    void updateCandidateVenueJob(CandidateVenueJobDto candidateVenueJobDto) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobDto findCandidateVenueJobById(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobPaginationDto findAllCandidateByVenueId(Long venueId, Pageable pageable) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobCountAllDto countCandidatesByVenue(Long venueId) throws CandidateVenueJobNotFoundException;

    List<CandidateVenueJobDto> findCandidateVenueJobByLastName(String lastName) throws CandidateVenueJobNotFoundException;

    List<CandidateVenueJobDto> findCandidateVenueJobByDESC(Long venueId) throws CandidateVenueJobNotFoundException;

    List<CandidateVenueJobDto> findCandidateVenueJobByASC(Long venueId) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobPaginationDto findAllCandidateVenueJobByDESC(Pageable pageable) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobPaginationDto findAllCandidateVenueJobByASC(Pageable pageable) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobPaginationDto findCandidateVenueJobByCurrentLevel(String currentLevel,Pageable pageable) throws CandidateVenueJobNotFoundException;
}
