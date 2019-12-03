package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.entity.Job;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.JobNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.JobMapper;
import com.elca.jobfairmanagementsystem.repository.JobRepository;
import com.elca.jobfairmanagementsystem.service.JobService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobMapper = jobMapper;
        this.jobRepository = jobRepository;
    }

    @Override
    public void saveJob(JobDto jobDto) {
        var saveJobData = jobMapper.jobDtoToEntity(jobDto);
        jobRepository.save(saveJobData);
    }

    @Override
    public JobDto findJobById(Long jobId) throws JobNotFoundException {
        Optional<Job> getJobById = jobRepository.findById(jobId);
        var job = getJobById.orElseThrow(() -> new JobNotFoundException(ErrorMessages.JOB_NOT_FOUND.toString()));
        return jobMapper.jobEntityToDto(job);
    }

    @Override
    public List<JobDto> findAllJobs() throws JobNotFoundException {
        List<Job> getJobList = jobRepository.findAll();
        if (getJobList.size() != 0) {
            return getJobList.stream().map(jobMapper::jobEntityToDto).collect(Collectors.toList());
        } else {
            throw new JobNotFoundException(ErrorMessages.NO_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public void updateJob(JobDto jobDto) throws JobNotFoundException {
        var getJobById = findJobById(jobDto.getJobId());
        if (getJobById != null) {
            getJobById.setDescription(jobDto.getDescription());
            getJobById.setMinimumExperience(jobDto.getMinimumExperience());
            getJobById.setQualificationNeeded(jobDto.getQualificationNeeded());
            jobRepository.save(jobMapper.jobDtoToEntity(getJobById));
        } else {
            throw new JobNotFoundException(ErrorMessages.JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public void deleteJob(Long jobId) throws JobNotFoundException {
        var job = findJobById(jobId);
        if (job != null) {
            jobRepository.deleteById(jobId);
        } else {
            throw new JobNotFoundException(ErrorMessages.JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public List<JobDto> findJobByCategory(String category) throws JobNotFoundException {
        List<Job> getJobByCategory = jobRepository.findByCategory(category);
        if (getJobByCategory.size() != 0) {
            return getJobByCategory.stream().map(jobMapper::jobEntityToDto).collect(Collectors.toList());
        } else {
            throw new JobNotFoundException(ErrorMessages.JOB_NOT_FOUND.toString());
        }
    }
}
