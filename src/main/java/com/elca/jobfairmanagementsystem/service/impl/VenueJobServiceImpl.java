package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.*;
import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import com.elca.jobfairmanagementsystem.entity.QVenueJob;
import com.elca.jobfairmanagementsystem.entity.Venue;
import com.elca.jobfairmanagementsystem.entity.VenueJob;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.VenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.JobMapper;
import com.elca.jobfairmanagementsystem.mapper.VenueJobMapper;
import com.elca.jobfairmanagementsystem.repository.VenueJobRepository;
import com.elca.jobfairmanagementsystem.service.VenueJobService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final JobMapper jobMapper;

    public VenueJobServiceImpl(VenueJobRepository venueJobRepository, VenueJobMapper venueJobMapper, JobMapper jobMapper) {
        this.venueJobMapper = venueJobMapper;
        this.venueJobRepository = venueJobRepository;
        this.jobMapper = jobMapper;
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
    public VenueJobPaginationDto findByVenueId(long venueId, Pageable pageable) throws VenueJobNotFoundException {
        Page<VenueJob> findJobsByVenue = venueJobRepository.findByVenue(venueId,pageable);
        var jobsList = findJobsByVenue.stream().map(venueJobMapper::venueJobEntityToDto).collect(Collectors.toList());
        var paginationVenueJob = new VenueJobPaginationDto();
        paginationVenueJob.setVenueJobDtoList(jobsList);
        paginationVenueJob.setTotalElements(findJobsByVenue.getNumberOfElements());
        paginationVenueJob.setTotalPages(findJobsByVenue.getTotalPages());
        return paginationVenueJob;
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
    public List<VenueJobDto> findByTitleAndCategory(long venueId, String title, String category) throws VenueJobNotFoundException {
        List<VenueJob> findJobsByTitle = venueJobRepository.findByTitleAndCategory(venueId, title,category);
        if (findJobsByTitle.size() != 0) {
            return findJobsByTitle.stream()
                    .map(venueJobMapper::venueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.NO_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public List<VenueJobDto> findByLevelAndCategory(long venueId, String level, String category) throws VenueJobNotFoundException {
        List<VenueJob> findJobsByLevel = venueJobRepository.findByLevelAndCategory(venueId,level,category);
        if (findJobsByLevel.size() != 0) {
            return findJobsByLevel.stream()
                    .map(venueJobMapper::venueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new VenueJobNotFoundException(ErrorMessages.NO_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public VenueJob findByVenueIdAndJobId(long venueId, long jobId) throws VenueJobNotFoundException {
        Optional<VenueJob> getVenueJobByIdAndJobId = Optional.ofNullable(venueJobRepository.findByVenueIdAndJobId(venueId, jobId));
        return getVenueJobByIdAndJobId.orElseThrow(() -> new VenueJobNotFoundException(ErrorMessages.VENUE_JOB_NOT_FOUND.toString()));
    }

    @Override
    public void saveMultipleVenueJobs(VenueJobMultipleSaveDto venueJobMultipleSaveDto) throws VenueJobNotFoundException{
        List<JobDto> jobDtos = venueJobMultipleSaveDto.getJob();
        VenueJobDto venueJobDto = new VenueJobDto();
        if(jobDtos == null){
            throw new VenueJobNotFoundException(ErrorMessages.NO_VENUE_JOB_AVAILABLE.toString());
        }else {
            jobDtos.forEach(saveVenueJob ->{

                Long jobId = saveVenueJob.getJobId();
                Long venueId = venueJobMultipleSaveDto.getVenue().getVenueId();

                if(saveVenueJob.getChecked()){
                    VenueJob checkVenueJob = venueJobRepository.findByVenueIdAndJobId(venueId,jobId);
                    if(checkVenueJob == null){
                        venueJobDto.setJob(saveVenueJob);
                        venueJobDto.setVenue(venueJobMultipleSaveDto.getVenue());
                        venueJobDto.setVenueJobDate(venueJobMultipleSaveDto.getVenueJobDate());
                        VenueJob venueJob = venueJobMapper.venueJobDtoToEntity(venueJobDto);
                        venueJobRepository.save(venueJob);
                    }
                } if(!saveVenueJob.getChecked()){
                    deleteVenueJobByJobIdAndVenueId(venueId,jobId);
                }
            });
        }
    }

    @Override
    public void deleteVenueJobByJobIdAndVenueId(long venueId, long jobId) {
        venueJobRepository.deleteVenueJobByVenueIdAndJobId(venueId,jobId);
    }

    @Override
    public VenueJobPaginationDto findListOfJobs(String title, String position, String category,long venueId,Integer pageNumber, Integer pageSize) throws VenueJobNotFoundException {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        BooleanBuilder predicate = buildCandidatePredicate(title, position,category,venueId);
        Page<VenueJob> venueJobs = venueJobRepository.findAll(predicate, pageRequest);
        List<VenueJobDto> venueJobDtos = venueJobs.stream().map(venueJobMapper::venueJobEntityToDto).collect(Collectors.toList());
        var venueJobPagination = new VenueJobPaginationDto();
        venueJobPagination.setVenueJobDtoList(venueJobDtos);
        venueJobPagination.setTotalElements((int) venueJobs.getTotalElements());
        venueJobPagination.setTotalPages(venueJobs.getTotalPages());
        return venueJobPagination;
    }

    private BooleanBuilder buildCandidatePredicate(String title, String position, String category,long venueId) {
        var qVenueJob = QVenueJob.venueJob;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(!title.equals("")){
            booleanBuilder.and(qVenueJob.job.title.contains(title));
        }
        if(!position.equals("All")){
            booleanBuilder.and(qVenueJob.job.level.eq(position));
        }
        if(!category.equals("All")){
            booleanBuilder.and(qVenueJob.job.category.eq(category));
        }
        if(venueId != 0){
            booleanBuilder.and(qVenueJob.venue.venueId.eq(venueId));
        }
        return booleanBuilder;
    }
}