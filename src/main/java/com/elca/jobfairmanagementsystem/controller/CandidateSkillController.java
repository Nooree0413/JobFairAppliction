package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.CandidateSkillDto;
import com.elca.jobfairmanagementsystem.exception.CandidateSkillNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateSkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate-skill")
public class CandidateSkillController {
    private CandidateSkillService candidateSkillService;

    public CandidateSkillController(CandidateSkillService candidateSkillService) {
        this.candidateSkillService = candidateSkillService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidateSkillDto>> getAllCandidateSkills() throws CandidateSkillNotFoundException {
        return new ResponseEntity<>(candidateSkillService.findAllCandidateSkills(), HttpStatus.OK);
    }

    @GetMapping("/{candidateSkillId}")
    public ResponseEntity<CandidateSkillDto> getCandidateSkillById(@PathVariable Long candidateSkillId) throws CandidateSkillNotFoundException{
        return new ResponseEntity<>(candidateSkillService.findCandidateSkillById(candidateSkillId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveCandidateSkill(@RequestBody List<CandidateSkillDto> candidateSkillDtos) {
        candidateSkillService.saveCandidateSkill(candidateSkillDtos);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{candidateSkillId}")
    public ResponseEntity updateCandidateSkill(@RequestBody CandidateSkillDto candidateSkillDto) throws CandidateSkillNotFoundException{
        candidateSkillService.updateCandidateSkill(candidateSkillDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{candidateSkillId}")
    public ResponseEntity deleteCandidateSkill(@PathVariable Long candidateSkillId) throws CandidateSkillNotFoundException{
        candidateSkillService.deleteCandidateSkill(candidateSkillId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<CandidateSkillDto>> getCandidateSkillByCandidateId(@PathVariable Long candidateId) throws CandidateSkillNotFoundException {
//        return new ResponseEntity<>(candidateSkillService.findCandidateSkillByCandidateId(candidateId), HttpStatus.OK);
        return null;
    }
}
