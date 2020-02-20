package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.CandidateFileDto;
import com.elca.jobfairmanagementsystem.entity.Candidate;
import com.elca.jobfairmanagementsystem.entity.CandidateFile;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.mapper.CandidateFileMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateFileRepository;
import com.elca.jobfairmanagementsystem.repository.CandidateRepository;
import com.elca.jobfairmanagementsystem.service.CandidateFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class CandidateFileServiceImpl implements CandidateFileService {

    private final CandidateRepository candidateRepository;
    private final CandidateFileRepository candidateFileRepository;
    private final CandidateFileMapper candidateFileMapper;

    public CandidateFileServiceImpl(CandidateRepository candidateRepository, CandidateFileRepository candidateFileRepository, CandidateFileMapper candidateFileMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateFileRepository = candidateFileRepository;
        this.candidateFileMapper = candidateFileMapper;
    }

    @Override
    public void saveCandidateCv(MultipartFile file, Long candidateId, String fileName) throws IOException {
        CandidateFile candidateFile = new CandidateFile();
        candidateFile.setFileName(fileName);
        candidateFile.setData(file.getBytes());
        candidateFile.setFileType(file.getContentType());
        try {
            Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new CandidateNotFoundException("Candidate not found with id: " + candidateId));
            candidateFile.setCandidate(candidate);
        } catch (CandidateNotFoundException e) {
            throw new IOException(ErrorMessages.FILE_NOT_FOUND.toString());
        }
        candidateFileRepository.save(candidateFile);
    }

    @Override
    public CandidateFileDto getCandidateFileByFileName(String fileName) throws CandidateNotFoundException {
        Optional<CandidateFile> candidateFile = Optional.ofNullable(candidateFileRepository.findByFileName(fileName));
        var candidate = candidateFile.orElseThrow(() -> new CandidateNotFoundException("Candidate not found!"));
        return candidateFileMapper.candidateFileEntityToDto(candidate);
    }
}
