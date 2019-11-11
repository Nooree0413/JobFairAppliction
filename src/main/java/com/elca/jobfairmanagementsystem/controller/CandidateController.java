package com.elca.jobfairmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ghr
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate/")
public class CandidateController {

}
