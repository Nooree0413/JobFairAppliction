package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.JobCategoryDto;
import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.dto.JobPaginationDto;
import com.elca.jobfairmanagementsystem.entity.Job;
import com.elca.jobfairmanagementsystem.entity.QJob;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.JobNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.JobMapper;
import com.elca.jobfairmanagementsystem.repository.JobRepository;
import com.elca.jobfairmanagementsystem.service.JobService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public JobPaginationDto findAllJobs(Pageable pageable) throws JobNotFoundException {
        Page<Job> getJobList = jobRepository.findAll(pageable);
        if (getJobList == null) {
            throw new JobNotFoundException(ErrorMessages.NO_JOB_AVAILABLE.toString());
        } else {
            List<JobDto> jobDtos = getJobList.stream().map(jobMapper::jobEntityToDto).collect(Collectors.toList());
            JobPaginationDto paginationDto = new JobPaginationDto();
            paginationDto.setJobDtoList(jobDtos);
            paginationDto.setTotalElements(getJobList.getNumberOfElements());
            paginationDto.setTotalPages(getJobList.getTotalPages());
            return paginationDto;
        }
    }

    @Override
    public void updateJob(JobDto jobDto) throws JobNotFoundException {
        var getJobById = findJobById(jobDto.getJobId());
        if (getJobById != null) {
            getJobById.setTitle(jobDto.getTitle());
            getJobById.setCategory(jobDto.getCategory());
            getJobById.setLevel(jobDto.getLevel());
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

    @Override
    public List<JobDto> findJobsAppliedById(String jobPriority) throws JobNotFoundException {
        var removeFirstBracket = jobPriority.replace("[", "");
        var removeSecondBracket = removeFirstBracket.replace("]", "");
        String[] arrSplit = removeSecondBracket.split(",");
        List<JobDto> listJob = new ArrayList<>();
        for (String eachJobId : arrSplit) {
            var getJobId = Long.parseLong(eachJobId);
            try {
                JobDto job = findJobById(getJobId);
                listJob.add(job);
            } catch (JobNotFoundException e) {
                throw new JobNotFoundException(ErrorMessages.JOB_NOT_FOUND.toString());
            }
        }
        return listJob;
    }

    @Override
    public JobCategoryDto findCountJobCategory() {
        List<Job> jobs = jobRepository.findAll();
        JobCategoryDto jobCategoryDto = new JobCategoryDto();
        jobs.forEach(job -> {
            switch (job.getCategory()) {
                case "software-engineer":
                    jobCategoryDto.setSoftwareEngineer(1);
                    break;
                case "human-resource":
                    jobCategoryDto.setHumanResource(1);
                    break;
                case "manager":
                    jobCategoryDto.setManager(1);
                    break;
                case "business-analyst":
                    jobCategoryDto.setBusinessAnalyst(1);
                    break;
                case "architect":
                    jobCategoryDto.setArchitect(1);
                    break;
                case "quality-assurance":
                    jobCategoryDto.setQualityAssurance(1);
                    break;
                default:
                    jobCategoryDto.setSoftwareEngineer(0);
                    jobCategoryDto.setHumanResource(0);
                    jobCategoryDto.setManager(0);
                    jobCategoryDto.setBusinessAnalyst(0);
                    jobCategoryDto.setArchitect(0);
                    jobCategoryDto.setQualityAssurance(0);
                    break;
            }
        });
        return jobCategoryDto;
    }

    @Override
    public List<JobDto> findByTitle(String title) throws JobNotFoundException {
        List<Job> findJobsByTitle = jobRepository.findByTitle(title);
        if (findJobsByTitle.size() != 0) {
            return findJobsByTitle.stream()
                    .map(jobMapper::jobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new JobNotFoundException(ErrorMessages.JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public List<JobDto> findByLevel(String level) throws JobNotFoundException {
        List<Job> findJobsBylevel = jobRepository.findByLevel(level);
        if (findJobsBylevel.size() != 0) {
            return findJobsBylevel.stream()
                    .map(jobMapper::jobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new JobNotFoundException(ErrorMessages.JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public JobPaginationDto findListOfJobs(String title, String position, String category, Integer pageNumber, Integer pageSize) throws JobNotFoundException {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        BooleanBuilder predicate = buildJobJobPredicate(title, position, category);
        Page<Job> jobs = jobRepository.findAll(predicate, pageRequest);
        List<JobDto> jobsDto = jobs.stream().map(jobMapper::jobEntityToDto).collect(Collectors.toList());
        var jobPagination = new JobPaginationDto();
        jobPagination.setJobDtoList(jobsDto);
        jobPagination.setTotalElements((int) jobs.getTotalElements());
        jobPagination.setTotalPages(jobs.getTotalPages());
        return jobPagination;
    }

    private BooleanBuilder buildJobJobPredicate(String title, String position, String category) {
        var qJob = QJob.job;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!title.equals("")) {
            booleanBuilder.and(qJob.job.title.contains(title));
        }
        if (!position.equals("All")) {
            booleanBuilder.and(QJob.job.level.eq(position));
        }
        if (!category.equals("All")) {
            booleanBuilder.and(qJob.job.category.eq(category));
        }
        return booleanBuilder;
    }
}
