package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.entity.VenueJob;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.VenueJobMapper;
import com.elca.jobfairmanagementsystem.repository.VenueJobRepository;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import org.springframework.data.domain.Pageable;
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
            getVenueJobId.setJob(venueJobDto.getJob());
            getVenueJobId.setVenue(venueJobDto.getVenue());
            getVenueJobId.setVenueJobDate(venueJobDto.getVenueJobDate());
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

    @Override
    public List<VenueJobDto> findByVenueId(long venueId, Pageable pageable) throws VenueJobNotFoundException {
        List<VenueJob> findJobsByVenue = venueJobRepository.findByVenue(venueId,pageable);
        if (findJobsByVenue.size() != 0) {
            return findJobsByVenue.stream()
                    .map(venueJobMapper::venueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.NO_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public List<VenueJobDto> findByVenueIdAndCategory(long venueId, String category) throws VenueJobNotFoundException {
        List<VenueJob> findJobsByVenueAndCategory = venueJobRepository.findByVenueIdAndCategory(venueId,category);
        if (findJobsByVenueAndCategory.size() != 0) {
            return findJobsByVenueAndCategory.stream()
                    .map(venueJobMapper::venueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.NO_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public List<VenueJobDto> findByLevel(long venueId,String level) throws VenueJobNotFoundException {
        List<VenueJob> findJobslevel = venueJobRepository.findByLevel(venueId,level);
        if (findJobslevel.size() != 0) {
            return findJobslevel.stream()
                    .map(venueJobMapper::venueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.NO_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public List<VenueJobDto> findByTitle(long venueId, String title) throws VenueJobNotFoundException {
        List<VenueJob> findJobsByTitle = venueJobRepository.findByTitle(venueId, title);
        if (findJobsByTitle.size() != 0) {
            return findJobsByTitle.stream()
                    .map(venueJobMapper::venueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.NO_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public VenueJob findByVenueIdAndJobId(long venueId, long jobId) throws VenueJobNotFoundException {
        Optional<VenueJob> getVenueJobByIdAndJobId = Optional.ofNullable(venueJobRepository.findByVenueIdAndJobId(venueId, jobId));
        var venueJob = getVenueJobByIdAndJobId.orElseThrow(() -> new VenueJobNotFoundException(ErrorMessages.VENUE_JOB_NOT_FOUND.toString()));
        return venueJob;
    }
}