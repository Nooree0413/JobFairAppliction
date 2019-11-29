package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.exception.JobNotFoundException;
import com.elca.jobfairmanagementsystem.service.JobService;
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
    public ResponseEntity<List<JobDto>> getAllJobs() throws JobNotFoundException {
        return new ResponseEntity<>(jobService.findAllJobs(), HttpStatus.OK);
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
}
