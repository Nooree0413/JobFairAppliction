package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.JobDto;

import java.util.List;

public interface JobService {
    void saveJob(JobDto jobDto);
    JobDto searchJobById (Long jobId);
    List<JobDto> searchAllJobs();
    void updateJob(JobDto jobDto);
    void deleteJob(Long jobId);
}
