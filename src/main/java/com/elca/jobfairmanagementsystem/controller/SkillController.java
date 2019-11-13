package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.SkillDto;
import com.elca.jobfairmanagementsystem.service.SkillService;
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
@RequestMapping("/skill/")
public class SkillController {
    private final SkillService skillService;

    public SkillController(SkillService skillService){
        this.skillService=skillService;
    }
    @PostMapping("addSkill")
    public ResponseEntity saveSkill(@RequestBody SkillDto skillDto){
        skillService.saveSkill(skillDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("update/{skillId}")
    public ResponseEntity updateSkill(@RequestBody SkillDto skillDto){
        skillService.updateSkill(skillDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getSkill/{skillId}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long skillId){
        return new ResponseEntity<>(skillService.findSkillById(skillId),HttpStatus.FOUND);
    }

    @GetMapping("allSkills")
    public ResponseEntity<List<SkillDto>> getAllSkills(){
        return new ResponseEntity<>(skillService.searchAllSkills(),HttpStatus.FOUND);
    }
}
