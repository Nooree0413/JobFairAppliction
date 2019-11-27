package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.entity.VenueJob;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.exception.VenueNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.VenueJobMapper;
import com.elca.jobfairmanagementsystem.repository.VenueJobRepository;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VenueJobServiceImpl implements VenueJobService {

    private final VenueJobRepository venueJobRepository;
    private final VenueJobMapper venueJobMapper;

    public VenueJobServiceImpl(VenueJobRepository venueJobRepository, VenueJobMapper venueJobMapper) {
        this.venueJobMapper = venueJobMapper;
        this.venueJobRepository = venueJobRepository;
    }

    @Override
    public void saveVenueJob(VenueJobDto venueJobDto) {
        var saveVenueJobData = venueJobMapper.venueJobDtoToEntity(venueJobDto);
        venueJobRepository.save(saveVenueJobData);
    }

    @Override
    public VenueJobDto findVenueJobById(Long venueJobId) throws VenueJobNotFoundException {
        Optional<VenueJob> getVenueJobById = venueJobRepository.findById(venueJobId);
        var venueJob = getVenueJobById.orElseThrow(() -> new VenueJobNotFoundException(ErrorMessages.VENUE_JOB_NOT_FOUND.toString()));
        return venueJobMapper.venueJobEntityToDto(venueJob);
    }

    @Override
    public List<VenueJobDto> findAllVenueJobs() throws VenueJobNotFoundException {
        List<VenueJob> getVenueJobList = venueJobRepository.findAll();
        if (getVenueJobList.size() != 0) {
            return getVenueJobList.stream()
                    .map(venueJobMapper::venueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.NO_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public void updateVenueJob(VenueJobDto venueJobDto) throws VenueJobNotFoundException {
        var getVenueJobId = findVenueJobById(venueJobDto.getVenueJobId());
        if (getVenueJobId != null) {
            getVenueJobId.setJobId(venueJobDto.getJobId());
            getVenueJobId.setVenueId(venueJobDto.getVenueId());
            venueJobRepository.save(venueJobMapper.venueJobDtoToEntity(getVenueJobId));
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.VENUE_JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public void deleteVenueJob(Long venueJobId) throws VenueJobNotFoundException {
        var getVenueJob = findVenueJobById(venueJobId);
        if (getVenueJob != null) {
            venueJobRepository.deleteById(venueJobId);
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.VENUE_JOB_NOT_FOUND.toString());
        }
    }
}