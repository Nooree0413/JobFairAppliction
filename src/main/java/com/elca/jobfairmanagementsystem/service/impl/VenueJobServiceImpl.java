package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.entity.VenueJob;
import com.elca.jobfairmanagementsystem.mapper.VenueJobMapper;
import com.elca.jobfairmanagementsystem.repository.VenueJobRepository;
import com.elca.jobfairmanagementsystem.repository.VenueRepository;
import com.elca.jobfairmanagementsystem.service.VenueJobService;

import java.util.List;
import java.util.Optional;

public class VenueJobServiceImpl implements VenueJobService {

    private final VenueJobRepository venueJobRepository;
    private final VenueJobMapper venueJobMapper;

    public VenueJobServiceImpl (VenueJobRepository venueJobRepository,VenueJobMapper venueJobMapper){
        this.venueJobMapper = venueJobMapper;
        this.venueJobRepository = venueJobRepository;
    }

    @Override
    public void saveVenueJob(VenueJobDto venueJobDto) {
//        var saveVenueJobData = venueJobMapper.venueJobDtoToEntity(venueJobDto);

    }

    @Override
    public VenueJobDto searchVenueJobById(Long venueJobId) {
//        List<Long> getVenueJobById = venueJobRepository.findAll();
        return null;
    }

    @Override
    public List<VenueJobDto> searchAllVenueJobs() {
        return null;
    }

    @Override
    public void updateVenueJob(VenueJobDto venueJobDto) {

    }

    @Override
    public void deleteVenueJob(Long venueJobId) {

    }
}
