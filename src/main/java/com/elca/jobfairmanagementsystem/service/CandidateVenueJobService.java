package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;

import java.util.List;

public interface CandidateVenueJobService {
    List<CandidateVenueJobDto> findAllCandidateVenueJobs() throws CandidateVenueJobNotFoundException;

    void saveCandidateVenueJob(CandidateVenueJobDto candidateVenueJobDto);

    void deleteCandidateVenueJob(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException;

    void updateCandidateVenueJob(CandidateVenueJobDto candidateVenueJobDto) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobDto findCandidateVenueJobById(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException;

    List<CandidateVenueJobDto> findAllCandidateByVenueId(Long venueId) throws CandidateVenueJobNotFoundException;
}
