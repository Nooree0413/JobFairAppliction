package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.JobCategoryDto;
import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.dto.JobPaginationDto;
import com.elca.jobfairmanagementsystem.dto.JobPaginationDto;
import com.elca.jobfairmanagementsystem.exception.JobNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
    void saveJob(JobDto jobDto);

    JobDto findJobById(Long jobId) throws JobNotFoundException;

    JobPaginationDto findAllJobs(Pageable pageable) throws JobNotFoundException;

    void updateJob(JobDto jobDto) throws JobNotFoundException;

    void deleteJob(Long jobId) throws JobNotFoundException;

    List<JobDto> findJobByCategory(String category) throws JobNotFoundException;

    List<JobDto> findJobsAppliedById(String jobPriority) throws JobNotFoundException;

    JobCategoryDto findCountJobCategory() throws JobNotFoundException;

    List<JobDto> findByTitle(String title) throws JobNotFoundException;

    List<JobDto> findByLevel(String level) throws JobNotFoundException;

    JobPaginationDto findListOfJobs(String title,String position,String category, Integer pageNumber, Integer pageSize) throws  JobNotFoundException;
}
