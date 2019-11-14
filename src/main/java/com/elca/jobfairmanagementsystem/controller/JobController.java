package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.JobDto;
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
    public ResponseEntity<List<JobDto>> getAllJobs() {
        return new ResponseEntity<>(jobService.searchAllJobs(), HttpStatus.FOUND);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long jobId){
        return new ResponseEntity<>(jobService.searchJobById(jobId),HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity saveJob(@RequestBody JobDto jobDto){
        jobService.saveJob(jobDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity updateJob(@RequestBody JobDto jobDto){
        jobService.updateJob(jobDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity deleteJob(@PathVariable Long jobId){
        jobService.deleteJob(jobId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
