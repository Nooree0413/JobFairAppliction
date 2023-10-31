package com.elca.jobfairmanagementsystem;


import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.dto.JobDto;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.exception.JobNotFoundException;
import com.elca.jobfairmanagementsystem.service.CandidateService;
import com.elca.jobfairmanagementsystem.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JobFairManagementSystemApplicationTests {

    @Autowired
    private JobService jobService;
    @Autowired
    private CandidateService candidateService;

	@Test
	void contextLoads() {

	}

	@Test
     void getJobById() throws JobNotFoundException {
        JobDto getJob = jobService.findJobById(1L);
        assertThat(getJob.getTitle().equals("Software"));
    }

    @Test
    void getCandidateById() throws CandidateNotFoundException {
        CandidateDto getCandidate = candidateService.findCandidateById((long) 42);
        assertThat(getCandidate.getAddress().equals("Candos Quatre Bornes"));
    }

}
