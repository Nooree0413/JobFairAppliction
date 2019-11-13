package com.elca.jobfairmanagementsystem.controller;

import com.elca.jobfairmanagementsystem.dto.SkillDto;
import com.elca.jobfairmanagementsystem.mapper.SkillMapper;
import com.elca.jobfairmanagementsystem.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author bfk
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/skill/")
public class SkillController {
    private final SkillService skillService;
    private final SkillMapper skillMapper;

    public SkillController(SkillService skillService, SkillMapper skillMapper){
        this.skillMapper=skillMapper;
        this.skillService=skillService;
    }
    @PostMapping()
    public ResponseEntity saveSkill(@RequestBody SkillDto skillDto){
        skillService.saveSkill(skillDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

//    @PutMapping(".update/{id}")
//    public ResponseEntity updateSkill
}
