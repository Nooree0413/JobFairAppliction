package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.JobCategoryDto;
import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.dto.JobPaginationDto;
import com.elca.jobfairmanagementsystem.exception.JobNotFoundException;
import com.elca.jobfairmanagementsystem.service.JobService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/job")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping("/all")
    public ResponseEntity<JobPaginationDto> getAllJobs(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws JobNotFoundException {
        return new ResponseEntity<>(jobService.findAllJobs(PageRequest.of(pageNumber,pageSize)), HttpStatus.OK);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long jobId) throws JobNotFoundException{
        return new ResponseEntity<>(jobService.findJobById(jobId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveJob(@RequestBody JobDto jobDto){
        jobService.saveJob(jobDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity updateJob(@RequestBody JobDto jobDto) throws JobNotFoundException{
        jobService.updateJob(jobDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity deleteJob(@PathVariable Long jobId) throws JobNotFoundException{
        jobService.deleteJob(jobId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<JobDto>> getJobByCategory(@PathVariable String category) throws JobNotFoundException{
        return new ResponseEntity<>(jobService.findJobByCategory(category),HttpStatus.OK);
    }

    @GetMapping("/priority")
    public ResponseEntity <List<JobDto>> getJobByPriority(@PathVariable String jobId) throws JobNotFoundException{
        return new ResponseEntity<>(jobService.findJobsAppliedById(jobId),HttpStatus.OK);
    }

    @GetMapping("/category/count")
    public ResponseEntity <JobCategoryDto> getJobCategoryCount() throws JobNotFoundException{
        return new ResponseEntity<>(jobService.findCountJobCategory(),HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity<List<JobDto>> getAllJobsByTitle(@RequestParam String title) throws JobNotFoundException {
        return new ResponseEntity<>(jobService.findByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/level")
    public ResponseEntity<List<JobDto>> getAllJobsByLevel(@RequestParam String level) throws JobNotFoundException {
        return new ResponseEntity<>(jobService.findByLevel(level), HttpStatus.OK);
    }
}
