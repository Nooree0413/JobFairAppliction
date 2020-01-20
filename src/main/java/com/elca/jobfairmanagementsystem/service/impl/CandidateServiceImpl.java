package com.elca.jobfairmanagementsystem.service.impl;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.elca.jobfairmanagementsystem.dto.*;
import com.elca.jobfairmanagementsystem.entity.*;
import com.elca.jobfairmanagementsystem.exception.*;
import com.elca.jobfairmanagementsystem.mapper.*;
import com.elca.jobfairmanagementsystem.repository.*;
import com.elca.jobfairmanagementsystem.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ghr
 */
@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    private final ExperienceMapper experienceMapper;
    private final QualificationMapper qualificationMapper;
    private final CandidateSkillMapper candidateSkillMapper;
    private final CandidateVenueJobMapper candidateVenueJobMapper;
    private final VenueJobService venueJobService;
    private final JobService jobService;
    private final CandidateFileService candidateFileService;

    public CandidateServiceImpl(CandidateMapper candidateMapper, CandidateRepository candidateRepository, ExperienceMapper experienceMapper, QualificationMapper qualificationMapper, CandidateSkillMapper candidateSkillMapper, CandidateVenueJobMapper candidateVenueJobMapper, VenueJobService venueJobService, JobService jobService, CandidateFileService candidateFileService) {
        this.candidateMapper = candidateMapper;
        this.candidateRepository = candidateRepository;
        this.experienceMapper = experienceMapper;
        this.qualificationMapper = qualificationMapper;
        this.candidateSkillMapper = candidateSkillMapper;
        this.candidateVenueJobMapper = candidateVenueJobMapper;
        this.venueJobService = venueJobService;
        this.jobService = jobService;
        this.candidateFileService = candidateFileService;
    }

    @Override
    public CandidatePaginationDto findAllCandidate(Pageable pageable) throws CandidateNotFoundException {
        Page<Candidate> listOfCandidate = candidateRepository.findAll(pageable);
        if(listOfCandidate == null){
            throw new CandidateNotFoundException(ErrorMessages.NO_CANDIDATE_AVAILABLE.toString());
        } else {
            List<CandidateDto> listOfCandidateDto = listOfCandidate.stream().map(candidateMapper::candidateEntityToCandidateDto).collect(Collectors.toList());
            CandidatePaginationDto candidatePaginationDto = new CandidatePaginationDto();
            candidatePaginationDto.setCandidateDtoList(listOfCandidateDto);
            candidatePaginationDto.setTotalElements(listOfCandidate.getNumberOfElements());
            candidatePaginationDto.setTotalPages(listOfCandidate.getTotalPages());
            return candidatePaginationDto;
        }
    }

    @Override
    public void saveCandidate(CandidateDto candidateDto) {
        Candidate candidate = candidateMapper.candidateDtoToCandidateEntity(candidateDto);
        List<ExperienceDto> experienceList = candidateDto.getExperienceDtos();
        List<QualificationDto> qualificationList = candidateDto.getQualificationDtos();
        List<CandidateSkillDto> candidateSkillList = candidateDto.getCandidateSkillDtos();
        List<CandidateVenueJobSaveDto> candidateVenueJobSaveList = candidateDto.getCandidateVenueJobSaveDto();

        List<Experience> experiences = experienceList.stream().map(experienceMapper::experienceDtoToEntity).collect(Collectors.toList());
        candidate.setExperiences(experiences);

        List<Qualification> qualifications = qualificationList.stream().map(qualificationMapper::qualificationDtoToEntity).collect(Collectors.toList());
        candidate.setQualifications(qualifications);

        List<CandidateSkill> candidateSkills = candidateSkillList.stream().map(candidateSkillMapper::candidateSkillDtoToEntity).collect(Collectors.toList());
        candidate.setCandidateSkills(candidateSkills);

        List<CandidateVenueJob> candidateVenueJobs = new ArrayList<>();
        candidateVenueJobSaveList.forEach(candidatesVenueDto -> {
            try {
                VenueJob venueJob = venueJobService.findByVenueIdAndJobId(candidatesVenueDto.getVenueId().getVenueId(), candidatesVenueDto.getJobId());
                candidatesVenueDto.setVenueJobId(venueJob.getVenueJobId());
                candidatesVenueDto.setJobId(venueJob.getJob().getJobId());
                CandidateVenueJob saveCandidateVenueJob = candidateVenueJobMapper.candidateVenueJobSaveDtoToEntity(candidatesVenueDto);
                saveCandidateVenueJob.setCandidate(candidate);
                candidateVenueJobs.add(saveCandidateVenueJob);
            } catch (VenueJobNotFoundException e) {
                e.printStackTrace();
            }
        });
        candidate.setCandidateVenueJobs(candidateVenueJobs);
        candidateRepository.save(candidate);
    }

    @Override
    public void deleteCandidate(Long candidateId) throws CandidateNotFoundException {
        var candidate = findCandidateById(candidateId);
        if (candidate != null) {
            candidateRepository.deleteById(candidateId);
        } else {
            throw new CandidateNotFoundException(ErrorMessages.CANDIDATE_NOT_FOUND.toString());
        }
    }

    @Override
    public CandidateDto findCandidateById(Long candidateId) throws CandidateNotFoundException {
        Optional<Candidate> findOneCandidate = candidateRepository.findById(candidateId);
        var candidate = findOneCandidate.orElseThrow(() -> new CandidateNotFoundException(ErrorMessages.CANDIDATE_NOT_FOUND.toString()));
        var candidateDto = candidateMapper.candidateEntityToCandidateDto(candidate);

        var candidateVenueJobSaveDtos = candidateDto.getCandidateVenueJobSaveDto();

        for (CandidateVenueJobSaveDto candidateVenueJobSaveDto : candidateVenueJobSaveDtos) {
            List<JobDto> jobList = null;
            try {
                jobList = jobService.findJobsAppliedById(candidateVenueJobSaveDto.getJobPriority());
            } catch (JobNotFoundException e) {
                jobList = Collections.EMPTY_LIST;
            }
            candidateVenueJobSaveDto.setJobList(jobList);
        }
        return candidateDto;
    }

    @Override
    public List<CandidateDto> findCandidateByVenueId(Long venueId) throws CandidateNotFoundException {
        List<Candidate> candidates = candidateRepository.findCandidatesByVenueId(venueId);
        if (candidates.size() != 0) {
            return candidates.stream()
                    .map(candidateMapper::candidateEntityToCandidateDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateNotFoundException(ErrorMessages.NO_CANDIDATE_AVAILABLE.toString());
        }
    }

    @Override
    public void saveCandidateCv(CandidateDto candidateCvDto,MultipartFile[] files) throws IOException {
        Candidate candidate = candidateMapper.candidateDtoToCandidateEntity(candidateCvDto);
        List<CandidateVenueJobSaveDto> candidateVenueJobSaveList = candidateCvDto.getCandidateVenueJobSaveDto();
        List<CandidateVenueJob> candidateVenueJobs = new ArrayList<>();
        candidateVenueJobSaveList.forEach(candidatesVenueDto -> {
            try {
                VenueJob venueJob = venueJobService.findByVenueIdAndJobId(candidatesVenueDto.getVenueId().getVenueId(), candidatesVenueDto.getJobId());
                candidatesVenueDto.setVenueJobId(venueJob.getVenueJobId());
                candidatesVenueDto.setJobId(venueJob.getJob().getJobId());
                CandidateVenueJob saveCandidateVenueJob = candidateVenueJobMapper.candidateVenueJobSaveDtoToEntity(candidatesVenueDto);
                saveCandidateVenueJob.setCandidate(candidate);
                candidateVenueJobs.add(saveCandidateVenueJob);
            } catch (VenueJobNotFoundException e) {
                e.printStackTrace();
            }
        });
        candidate.setCandidateVenueJobs(candidateVenueJobs);
        candidateRepository.save(candidate);

        AtomicInteger incrementNumber = new AtomicInteger(1);
        Arrays.asList(files).forEach(candidateCv->{
            try {
                if(files.length < 2){
                    String extension = candidateCv.getOriginalFilename().split("\\.")[1];
                    String fullName = candidate.getFirstName().concat("-" + candidate.getLastName() + "." + extension);
                    candidateFileService.saveCandidateCv(candidateCv,candidate.getCandidateId(),fullName);
                } else {
                    String extension = candidateCv.getOriginalFilename().split("\\.")[1];
                    String fullName = candidate.getFirstName().concat("-" + candidate.getLastName()).concat("-" + incrementNumber + "." + extension);
                    candidateFileService.saveCandidateCv(candidateCv,candidate.getCandidateId(),fullName);
                    incrementNumber.getAndIncrement();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void updateCandidate(CandidateDto candidateDto) throws CandidateNotFoundException {
        CandidateDto candidate = findCandidateById(candidateDto.getCandidateId());
        if (candidate != null) {
            candidate.setAddress(candidateDto.getAddress());
            candidate.setFirstName(candidateDto.getFirstName());
            candidate.setLastName(candidateDto.getLastName());
            candidate.setEmail(candidateDto.getEmail());
            candidate.setNationality(candidateDto.getNationality());
            saveCandidate(candidate);
        } else {
            throw new CandidateNotFoundException(ErrorMessages.CANDIDATE_NOT_FOUND.toString());
        }
    }
}
