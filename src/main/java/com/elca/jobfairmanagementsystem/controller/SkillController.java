package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.SkillDto;
import com.elca.jobfairmanagementsystem.dto.SkillPaginationDto;
import com.elca.jobfairmanagementsystem.exception.SkillNotFoundException;
import com.elca.jobfairmanagementsystem.service.SkillService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author bfk
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/skill")
public class SkillController {

    private final SkillService skillService;
    public SkillController(SkillService skillService){
        this.skillService=skillService;
    }

    @PostMapping
    public ResponseEntity saveSkill(@RequestBody SkillDto skillDto){
        skillService.saveSkill(skillDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{skillId}")
    public ResponseEntity updateSkill(@RequestBody SkillDto skillDto) throws SkillNotFoundException {
        skillService.updateSkill(skillDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{skillId}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long skillId) throws SkillNotFoundException{
        return new ResponseEntity<>(skillService.findSkillById(skillId),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<SkillPaginationDto> getAllSkills(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) throws SkillNotFoundException{
        return new ResponseEntity<>(skillService.findAllSkills(PageRequest.of(pageNumber,pageSize)),HttpStatus.OK);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity deleteSkill(@PathVariable Long skillId) throws SkillNotFoundException{
        skillService.deleteSkill(skillId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
