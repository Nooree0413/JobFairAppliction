package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.ExperienceDto;
import com.elca.jobfairmanagementsystem.exception.ExperienceNotFoundException;
import com.elca.jobfairmanagementsystem.service.ExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/experience")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExperienceDto>> getAllExperiences() throws ExperienceNotFoundException {
        return new ResponseEntity<>(experienceService.findAllExperience(), HttpStatus.OK);
    }

    @GetMapping("/{experienceId}")
    public ResponseEntity<ExperienceDto> searchExperienceById(@PathVariable Long experienceId) throws ExperienceNotFoundException {
        return new ResponseEntity<>(experienceService.findByExperienceId(experienceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveExperience(@RequestBody ExperienceDto experienceDto) {
        experienceService.saveExperience(experienceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{experienceId}")
    public ResponseEntity updateExperience(@RequestBody ExperienceDto experienceDto) throws ExperienceNotFoundException {
        experienceService.updateExperience(experienceDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{experienceId}")
    public ResponseEntity deleteExperience(@PathVariable Long experienceId) throws ExperienceNotFoundException {
        experienceService.deleteExperience(experienceId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<ExperienceDto>> getAllExperienceByCandidateId(@PathVariable Long candidateId) throws ExperienceNotFoundException {
        return new ResponseEntity<>(experienceService.findByCandidateId(candidateId), HttpStatus.OK);
    }
}
