package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.dto.VenueJobMultipleSaveDto;
import com.elca.jobfairmanagementsystem.dto.VenueJobPaginationDto;
import com.elca.jobfairmanagementsystem.entity.VenueJob;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VenueJobService {
    void saveVenueJob(VenueJobDto venueDto);

    VenueJobDto findVenueJobById(Long venueJobId) throws VenueJobNotFoundException;

    List<VenueJobDto> findAllVenueJobs() throws VenueJobNotFoundException;

    void updateVenueJob(VenueJobDto venueJobDto) throws VenueJobNotFoundException;

    void deleteVenueJob(Long venueJobId) throws VenueJobNotFoundException;

    VenueJobPaginationDto findByVenueId(long venueId, Pageable pageable) throws VenueJobNotFoundException;

    List<VenueJobDto> findByVenueIdAndCategory(long venueId, String category) throws VenueJobNotFoundException;

    List<VenueJobDto> findByLevel(long venueId, String level) throws VenueJobNotFoundException;

    List<VenueJobDto> findByTitle(long venueId, String title) throws VenueJobNotFoundException;

    List<VenueJobDto> findByTitleAndCategory(long venueId, String title, String category) throws VenueJobNotFoundException;

    List<VenueJobDto> findByLevelAndCategory(long venueId, String level, String category) throws VenueJobNotFoundException;

    VenueJob findByVenueIdAndJobId(long venueId, long jobId) throws VenueJobNotFoundException;

    void saveMultipleVenueJobs(VenueJobMultipleSaveDto venueJobMultipleSaveDto) throws VenueJobNotFoundException;

    void deleteVenueJobByJobIdAndVenueId(long venueId, long jobId) throws VenueJobNotFoundException;

    VenueJobPaginationDto findListOfJobs(String title, String position, String category, long venueId, Integer pageNumber, Integer pageSize) throws VenueJobNotFoundException;
}