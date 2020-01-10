package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobCountAllDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobDto;
import com.elca.jobfairmanagementsystem.dto.CandidateVenueJobSaveDto;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;

import java.util.List;

public interface CandidateVenueJobService {
    List<CandidateVenueJobDto> findAllCandidateVenueJobs() throws CandidateVenueJobNotFoundException;

    void deleteCandidateVenueJob(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException;

    void updateCandidateVenueJob(CandidateVenueJobDto candidateVenueJobDto) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobDto findCandidateVenueJobById(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException;

    List<CandidateVenueJobDto> findAllCandidateByVenueId(Long venueId,Boolean limit) throws CandidateVenueJobNotFoundException;

    CandidateVenueJobCountAllDto countCandidatesByVenue(Long venueId);

    List<CandidateVenueJobDto> findCandidateVenueJobByLastName(String lastName) throws CandidateVenueJobNotFoundException;

    List<CandidateVenueJobDto> findCandidateVenueJobByDESC (Long venueId) throws CandidateVenueJobNotFoundException;

    List<CandidateVenueJobDto> findCandidateVenueJobByASC (Long venueId) throws CandidateVenueJobNotFoundException;
}
