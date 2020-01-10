    package com.elca.jobfairmanagementsystem.service.impl;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    import com.elca.jobfairmanagementsystem.dto.*;
    import com.elca.jobfairmanagementsystem.entity.*;
    import com.elca.jobfairmanagementsystem.exception.*;
    import com.elca.jobfairmanagementsystem.mapper.*;
    import com.elca.jobfairmanagementsystem.repository.*;
    import com.elca.jobfairmanagementsystem.service.*;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import org.springframework.util.StringUtils;
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

        public CandidateServiceImpl(CandidateMapper candidateMapper, CandidateRepository candidateRepository, ExperienceMapper experienceMapper, QualificationMapper qualificationMapper, CandidateSkillMapper candidateSkillMapper, CandidateVenueJobMapper candidateVenueJobMapper, VenueJobService venueJobService,JobService jobService) {
            this.candidateMapper = candidateMapper;
            this.candidateRepository = candidateRepository;
            this.experienceMapper = experienceMapper;
            this.qualificationMapper = qualificationMapper;
            this.candidateSkillMapper = candidateSkillMapper;
            this.candidateVenueJobMapper = candidateVenueJobMapper;
            this.venueJobService = venueJobService;
            this.jobService = jobService;
        }

        @Override
        public List<CandidateDto> findAllCandidate() throws CandidateNotFoundException {
            List<Candidate> listOfCandidate = candidateRepository.findAll();
            if (listOfCandidate.size() != 0) {
                return listOfCandidate.stream()
                        .map(candidateMapper::candidateEntityToCandidateDto)
                        .collect(Collectors.toList());
            } else {
                throw new CandidateNotFoundException(ErrorMessages.NO_CANDIDATE_AVAILABLE.toString());
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
                    VenueJob venueJob = venueJobService.findByVenueIdAndJobId(candidatesVenueDto.getVenueId(), candidatesVenueDto.getJobId());
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
        public void saveCandidateCv(CandidateDto candidateCvDto,MultipartFile file) throws FileNotFoundException {
            Candidate candidate = candidateMapper.candidateDtoToCandidateEntity(candidateCvDto);
            List<CandidateVenueJobSaveDto> candidateVenueJobSaveList = candidateCvDto.getCandidateVenueJobSaveDto();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                if (fileName.contains("..")) {
                    throw new FileNotFoundException("Sorry! Filename contains invalid path sequence " + fileName);
                }
                candidate.setFileName(fileName);
                candidate.setData(file.getBytes());
                candidate.setFileType(file.getContentType());

                List<CandidateVenueJob> candidateVenueJobs = new ArrayList<>();
                candidateVenueJobSaveList.forEach(candidatesVenueDto -> {
                    try {
                        VenueJob venueJob = venueJobService.findByVenueIdAndJobId(candidatesVenueDto.getVenueId(), candidatesVenueDto.getJobId());
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

            } catch (IOException ex){
                throw new FileNotFoundException(ErrorMessages.FILE_NOT_FOUND.toString());
            }
        }

        @Override
        public Candidate findCandidateCvById(Long candidateId) throws FileNotFoundException {
            return candidateRepository.findById(candidateId).orElseThrow(() -> new FileNotFoundException("File not found with id: " + candidateId));
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
