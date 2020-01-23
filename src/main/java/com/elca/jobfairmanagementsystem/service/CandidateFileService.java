package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.CandidateFileDto;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CandidateFileService {
    void saveCandidateCv(MultipartFile file,Long candidateId,String fileName) throws IOException;
    CandidateFileDto getCandidateFileByFileName(String fileName) throws CandidateNotFoundException;
}
