package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.JobCategoryDto;
import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.exception.JobNotFoundException;

import java.util.List;

public interface JobService {
    void saveJob(JobDto jobDto);

    JobDto findJobById(Long jobId) throws JobNotFoundException;

    List<JobDto> findAllJobs() throws JobNotFoundException;

    void updateJob(JobDto jobDto) throws JobNotFoundException;

    void deleteJob(Long jobId) throws JobNotFoundException;

    List<JobDto> findJobByCategory(String category) throws JobNotFoundException;

    List<JobDto> findJobsAppliedById(String jobPriority) throws JobNotFoundException;

    JobCategoryDto findCountJobCategory() throws JobNotFoundException;
}
