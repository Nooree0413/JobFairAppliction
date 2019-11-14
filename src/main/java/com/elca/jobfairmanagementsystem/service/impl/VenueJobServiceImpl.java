package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.entity.VenueJob;
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

    public VenueJobServiceImpl (VenueJobRepository venueJobRepository,VenueJobMapper venueJobMapper){
        this.venueJobMapper = venueJobMapper;
        this.venueJobRepository = venueJobRepository;
    }

    @Override
    public void saveVenueJob(VenueJobDto venueJobDto) {
        var saveVenueJobData = venueJobMapper.venueJobDtoToEntity(venueJobDto);
        venueJobRepository.save(saveVenueJobData);
    }

    @Override
    public VenueJobDto searchVenueJobById(Long venueJobId) {
        Optional<VenueJob> getVenueJobById = venueJobRepository.findById(venueJobId);
        var venueJob = getVenueJobById.orElse(null);
        if(venueJob != null){
            return venueJobMapper.venueJobEntityToDto(venueJob);
        } else {
            return null;
        }
    }

    @Override
    public List<VenueJobDto> searchAllVenueJobs() {
        List<VenueJob> getVenueJobList = venueJobRepository.findAll();
        return getVenueJobList.stream()
                .map(venueJobMapper::venueJobEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateVenueJob(VenueJobDto venueJobDto) {
        var getVenueJobId = searchVenueJobById(venueJobDto.getVenueJobId());
        getVenueJobId.setJob(venueJobDto.getJob());
        getVenueJobId.setVenue(venueJobDto.getVenue());
        venueJobRepository.save(venueJobMapper.venueJobDtoToEntity(getVenueJobId));
    }

    @Override
    public void deleteVenueJob(Long venueJobId) {
        venueJobRepository.deleteById(venueJobId);
    }
}
