package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.CandidateDto;
import com.elca.jobfairmanagementsystem.dto.CandidatePaginationDto;
import com.elca.jobfairmanagementsystem.exception.CandidateNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ghr
 */
public interface CandidateService {

    CandidatePaginationDto findAllCandidate(Pageable pageable) throws CandidateNotFoundException;

    void saveCandidate(CandidateDto candidateDto);

    void deleteCandidate(Long candidateId) throws CandidateNotFoundException;

    void updateCandidate(CandidateDto candidateDto) throws CandidateNotFoundException;

    CandidateDto findCandidateById(Long candidateId) throws CandidateNotFoundException;

    List<CandidateDto> findCandidateByVenueId(Long venueId) throws CandidateNotFoundException;

    void saveCandidateCv(CandidateDto candidateDto, MultipartFile[] files) throws IOException;

    CandidatePaginationDto filterCandidates(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder, String venue, String screenStatus, String jobId);
}
