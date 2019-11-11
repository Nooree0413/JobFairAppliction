package com.elca.jobfairmanagementsystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bfk
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/skill/")
public class SkillController {
}
