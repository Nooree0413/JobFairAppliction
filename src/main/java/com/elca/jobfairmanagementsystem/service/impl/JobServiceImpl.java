package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.entity.Job;
import com.elca.jobfairmanagementsystem.mapper.JobMapper;
import com.elca.jobfairmanagementsystem.repository.JobRepository;
import com.elca.jobfairmanagementsystem.service.JobService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository,JobMapper jobMapper){
        this.jobMapper = jobMapper;
        this.jobRepository = jobRepository;
    }

    @Override
    public void saveJob(JobDto jobDto) {
        var saveJobData = jobMapper.jobDtoToEntity(jobDto);
        jobRepository.save(saveJobData);
    }

    @Override
    public JobDto searchJobById(Long jobId) {
        Optional<Job> getJobById = jobRepository.findById(jobId);
        var job = getJobById.orElse(null);
        if(job != null){
            return jobMapper.jobEntityToDto(job);
        } else {
            return null;
        }
    }

    @Override
    public List<JobDto> searchAllJobs() {
        List<Job> getJobList = jobRepository.findAll();
        return getJobList.stream().map(jobMapper::jobEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void updateJob(JobDto jobDto) {
        var getJobById = searchJobById(jobDto.getJobId());
        getJobById.setJobTitle(jobDto.getJobTitle());
        getJobById.setDescription(jobDto.getDescription());
        getJobById.setMinimumExperience(jobDto.getMinimumExperience());
        getJobById.setQualificationNeeded(jobDto.getQualificationNeeded());
        jobRepository.save(jobMapper.jobDtoToEntity(getJobById));
    }

    @Override
    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}
