package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.entity.VenueJob;

import java.util.List;

public interface VenueJobService {
    void saveVenueJob(VenueJobDto venueDto);
    VenueJobDto searchVenueJobById (Long venueJobId);
    List<VenueJobDto> searchAllVenueJobs();
    void updateVenueJob(VenueJobDto venueJobDto);
    void deleteVenueJob(Long venueJobId);
}
