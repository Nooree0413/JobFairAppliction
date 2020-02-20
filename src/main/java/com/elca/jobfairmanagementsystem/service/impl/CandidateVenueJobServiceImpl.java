package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.*;
import com.elca.jobfairmanagementsystem.entity.CandidateVenueJob;
import com.elca.jobfairmanagementsystem.entity.QCandidateVenueJob;
import com.elca.jobfairmanagementsystem.exception.CandidateVenueJobNotFoundException;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.mapper.CandidateVenueJobMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateVenueJobRepository;
import com.elca.jobfairmanagementsystem.repository.JobRepository;
import com.elca.jobfairmanagementsystem.repository.VenueJobRepository;
import com.elca.jobfairmanagementsystem.service.CandidateVenueJobService;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CandidateVenueJobServiceImpl implements CandidateVenueJobService {
    private final CandidateVenueJobMapper candidateVenueJobMapper;
    private final CandidateVenueJobRepository candidateVenueJobRepository;
    private final VenueJobRepository venueJobRepository;
    private final JobRepository jobRepository;

    public CandidateVenueJobServiceImpl(CandidateVenueJobMapper candidateVenueJobMapper, CandidateVenueJobRepository candidateVenueJobRepository, VenueJobRepository venueJobRepository, JobRepository jobRepository) {
        this.candidateVenueJobMapper = candidateVenueJobMapper;
        this.candidateVenueJobRepository = candidateVenueJobRepository;
        this.venueJobRepository = venueJobRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public CandidateVenueJobPaginationDto findAllCandidateVenueJobs(Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findAllCandidateVenueJobOrderByRegistrationDate(pageable);
        return getCandidateVenueJobPaginationDto(candidateVenueJobs);
    }

    @Override
    public void deleteCandidateVenueJob(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException {
        var candidateVenueJob = findCandidateVenueJobById(candidateVenueJobId);
        if (candidateVenueJob != null) {
            candidateVenueJobRepository.deleteById(candidateVenueJobId);
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.CANDIDATE_VENUE_JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public void updateCandidateVenueJob(CandidateVenueJobDto candidateVenueJobDto) throws CandidateVenueJobNotFoundException {
        var getCandidateVenueJobId = findCandidateVenueJobById(candidateVenueJobDto.getCandidateVenueJob());
        if (getCandidateVenueJobId != null) {
            getCandidateVenueJobId.setCandidate(candidateVenueJobDto.getCandidate());
            getCandidateVenueJobId.setVenueJob(candidateVenueJobDto.getVenueJob());
            getCandidateVenueJobId.setJobPriority(candidateVenueJobDto.getJobPriority());
            candidateVenueJobRepository.save(candidateVenueJobMapper.candidateVenueJobDtoToEntity(getCandidateVenueJobId));
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.CANDIDATE_VENUE_JOB_NOT_FOUND.toString());
        }
    }

    @Override
    public CandidateVenueJobDto findCandidateVenueJobById(Long candidateVenueJobId) throws CandidateVenueJobNotFoundException {
        Optional<CandidateVenueJob> getCandidateVenueJob = candidateVenueJobRepository.findById(candidateVenueJobId);
        var candidateVenueJob = getCandidateVenueJob.orElseThrow(() -> new CandidateVenueJobNotFoundException(ErrorMessages.CANDIDATE_VENUE_JOB_NOT_FOUND.toString()));
        return candidateVenueJobMapper.candidateVenueJobEntityToDto(candidateVenueJob);
    }

    @Override
    public CandidateVenueJobPaginationDto findAllCandidateByVenueId(Long venueId, Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findCandidatesByVenueId(venueId, pageable);
        return getCandidateVenueJobPaginationDto(candidateVenueJobs);
    }

    @Override
    public CandidateVenueJobCountAllDto countCandidatesByVenue(Long venueId) throws CandidateVenueJobNotFoundException {
        Integer counts = candidateVenueJobRepository.findCountOfCandidatesByVenueId(venueId);
        CandidateVenueJobCountAllDto candidateVenueJobCountAllDto = new CandidateVenueJobCountAllDto();
        candidateVenueJobCountAllDto.setCountCandidates(counts);
        return candidateVenueJobCountAllDto;
    }

    @Override
    public List<CandidateVenueJobDto> findCandidateVenueJobByLastName(String lastName) throws CandidateVenueJobNotFoundException {
        List<CandidateVenueJob> candidateVenueJobsByLastName = candidateVenueJobRepository.findByLastName(lastName);
        if (candidateVenueJobsByLastName.size() != 0) {
            return candidateVenueJobsByLastName.stream()
                    .map(candidateVenueJobMapper::candidateVenueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public List<CandidateVenueJobDto> findCandidateVenueJobByDESC(Long venueId) throws CandidateVenueJobNotFoundException {
        List<CandidateVenueJob> candidateVenueJobDESC = candidateVenueJobRepository.findCandidatesByVenueIdDESC(venueId);
        if (candidateVenueJobDESC.size() != 0) {
            return candidateVenueJobDESC.stream()
                    .map(candidateVenueJobMapper::candidateVenueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public List<CandidateVenueJobDto> findCandidateVenueJobByASC(Long venueId) throws CandidateVenueJobNotFoundException {
        List<CandidateVenueJob> candidateVenueJobASC = candidateVenueJobRepository.findCandidatesByVenueIdASC(venueId);
        if (candidateVenueJobASC.size() != 0) {
            return candidateVenueJobASC.stream()
                    .map(candidateVenueJobMapper::candidateVenueJobEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        }
    }

    @Override
    public CandidateVenueJobPaginationDto findAllCandidateVenueJobByDESC(Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findAllCandidatesByDESC(pageable);
        if (candidateVenueJobs == null) {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else {
            return getCandidateVenueJobPaginationDto(candidateVenueJobs);
        }
    }

    @Override
    public CandidateVenueJobPaginationDto findAllCandidateVenueJobByASC(Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findAllCandidatesByASC(pageable);
        if (candidateVenueJobs == null) {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else {
            return getCandidateVenueJobPaginationDto(candidateVenueJobs);
        }
    }

    @Override
    public CandidateVenueJobPaginationDto findCandidateVenueJobByCurrentLevel(String currentLevel, Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findByCurrentLevel(currentLevel, pageable);
        if (candidateVenueJobs == null) {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else {
            return getCandidateVenueJobPaginationDto(candidateVenueJobs);
        }
    }

    @Override
    public CandidateVenueJobPaginationDto findCandidateVenueJobByScreeningStatus(String screeningStatus, Pageable pageable) throws CandidateVenueJobNotFoundException {
        Page<CandidateVenueJob> candidateVenueJobs = candidateVenueJobRepository.findByScreeningStatus(screeningStatus, pageable);
        if (candidateVenueJobs == null) {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else {
            return getCandidateVenueJobPaginationDto(candidateVenueJobs);
        }
    }

    @Override
    public DashboardDto dashboardStatisticByVenue(Long venueId) throws CandidateVenueJobNotFoundException {

        Integer totalJobsByVenue = venueJobRepository.findCountOfJobsByVenue(venueId);
        Integer totalProceedScreeningStatusByVenue = candidateVenueJobRepository.findCountOfScreeningStatusByVenueId(venueId, "proceed-to-next-interview");
        Integer totalRejectedScreeningStatusByVenue = candidateVenueJobRepository.findCountOfScreeningStatusByVenueId(venueId, "Rejected");
        Integer totalAcceptedScreeningStatusByVenue = candidateVenueJobRepository.findCountOfScreeningStatusByVenueId(venueId, "Accepted");

        Integer totalCandidatesPerSoftwareEngineerByVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByVenue(venueId, "software-engineer");
        Integer totalCandidatesPerBusinessAnalystByVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByVenue(venueId, "business-analyst");
        Integer totalCandidatesPerQualityAssuranceByVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByVenue(venueId, "quality-assurance");
        Integer totalCandidatesPerManagerByVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByVenue(venueId, "manager");
        Integer totalCandidatesPerHumanResourceByVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByVenue(venueId, "human-resource");
        Integer totalCandidatesPerArchitectByVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByVenue(venueId, "architect");

        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setTotalJobsByVenue(totalJobsByVenue);
        dashboardDto.setTotalRejectedScreeningStatusByVenue(totalRejectedScreeningStatusByVenue);
        dashboardDto.setTotalProceedScreeningStatusByVenue(totalProceedScreeningStatusByVenue);
        dashboardDto.setTotalApprovedScreeningStatusByVenue(totalAcceptedScreeningStatusByVenue);
        dashboardDto.setTotalCandidatesPerSoftwareEngineerByVenue(totalCandidatesPerSoftwareEngineerByVenue);
        dashboardDto.setTotalCandidatesPerBusinessAnalystByVenue(totalCandidatesPerBusinessAnalystByVenue);
        dashboardDto.setTotalCandidatesPerQualityAssuranceByVenue(totalCandidatesPerQualityAssuranceByVenue);
        dashboardDto.setTotalCandidatesPerManagerByVenue(totalCandidatesPerManagerByVenue);
        dashboardDto.setTotalCandidatesPerHumanResourceByVenue(totalCandidatesPerHumanResourceByVenue);
        dashboardDto.setTotalCandidatesPerArchitectByVenue(totalCandidatesPerArchitectByVenue);

        CandidatesPerMonthDto candidatesPerMonthDto = new CandidatesPerMonthDto();
        candidatesPerMonthDto.setTotalCandidatesForJanuary(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 1));
        candidatesPerMonthDto.setTotalCandidatesForFebruary(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 2));
        candidatesPerMonthDto.setTotalCandidatesForMarch(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 3));
        candidatesPerMonthDto.setTotalCandidatesForApril(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 4));
        candidatesPerMonthDto.setTotalCandidatesForMay(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 5));
        candidatesPerMonthDto.setTotalCandidatesForJune(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 6));
        candidatesPerMonthDto.setTotalCandidatesForJuly(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 7));
        candidatesPerMonthDto.setTotalCandidatesForAugust(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 8));
        candidatesPerMonthDto.setTotalCandidatesForSeptember(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 9));
        candidatesPerMonthDto.setTotalCandidatesForOctober(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 10));
        candidatesPerMonthDto.setTotalCandidatesForNovember(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 11));
        candidatesPerMonthDto.setTotalCandidatesForDecember(candidateVenueJobRepository.findCandidatesPerMonthByVenue(venueId, 12));
        dashboardDto.setTotalCandidatesPerMonth(candidatesPerMonthDto);
        return dashboardDto;
    }

    @Override
    public DashboardDto dashboardStatisticByAllVenue() throws CandidateVenueJobNotFoundException {
        Integer totalJobsByAllVenue = jobRepository.findCountOfAllJobs();
        Integer totalCandidatesByAllVenue = candidateVenueJobRepository.findCountOfCandidates();
        Integer totalProceedScreeningStatusByAllVenue = candidateVenueJobRepository.findCountOfScreeningStatusByAllVenue("proceed-to-next-interview");
        Integer totalRejectedScreeningStatusByAllVenue = candidateVenueJobRepository.findCountOfScreeningStatusByAllVenue("Rejected");
        Integer totalAcceptedScreeningStatusByAllVenue = candidateVenueJobRepository.findCountOfScreeningStatusByAllVenue("Accepted");

        Integer totalCandidatesPerSoftwareEngineerByAllVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByAllVenue("software-engineer");
        Integer totalCandidatesPerBusinessAnalystByAllVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByAllVenue("business-analyst");
        Integer totalCandidatesPerQualityAssuranceByAllVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByAllVenue("quality-assurance");
        Integer totalCandidatesPerManagerByAllVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByAllVenue("manager");
        Integer totalCandidatesPerHumanResourceByAllVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByAllVenue("human-resource");
        Integer totalCandidatesPerArchitectByAllVenue = candidateVenueJobRepository.findCountOfCandidatesPerJobCategoryByAllVenue("architect");

        DashboardDto dashboardDto = new DashboardDto();
        dashboardDto.setTotalJobsByAllVenue(totalJobsByAllVenue);
        dashboardDto.setTotalCandidatesByAllVenue(totalCandidatesByAllVenue);
        dashboardDto.setTotalProceedScreeningStatusByAllVenue(totalProceedScreeningStatusByAllVenue);
        dashboardDto.setTotalRejectedScreeningStatusByAllVenue(totalRejectedScreeningStatusByAllVenue);
        dashboardDto.setTotalApprovedScreeningStatusByAllVenue(totalAcceptedScreeningStatusByAllVenue);

        dashboardDto.setTotalCandidatesPerSoftwareEngineerByAllVenue(totalCandidatesPerSoftwareEngineerByAllVenue);
        dashboardDto.setTotalCandidatesPerBusinessAnalystByAllVenue(totalCandidatesPerBusinessAnalystByAllVenue);
        dashboardDto.setTotalCandidatesPerQualityAssuranceByAllVenue(totalCandidatesPerQualityAssuranceByAllVenue);
        dashboardDto.setTotalCandidatesPerManagerByAllVenue(totalCandidatesPerManagerByAllVenue);
        dashboardDto.setTotalCandidatesPerHumanResourceByAllVenue(totalCandidatesPerHumanResourceByAllVenue);
        dashboardDto.setTotalCandidatesPerArchitectByAllVenue(totalCandidatesPerArchitectByAllVenue);

        CandidatesPerMonthDto candidatesPerMonthDto = new CandidatesPerMonthDto();
        candidatesPerMonthDto.setTotalCandidatesForJanuary(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(1));
        candidatesPerMonthDto.setTotalCandidatesForFebruary(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(2));
        candidatesPerMonthDto.setTotalCandidatesForMarch(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(3));
        candidatesPerMonthDto.setTotalCandidatesForApril(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(4));
        candidatesPerMonthDto.setTotalCandidatesForMay(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(5));
        candidatesPerMonthDto.setTotalCandidatesForJune(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(6));
        candidatesPerMonthDto.setTotalCandidatesForJuly(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(7));
        candidatesPerMonthDto.setTotalCandidatesForAugust(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(8));
        candidatesPerMonthDto.setTotalCandidatesForSeptember(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(9));
        candidatesPerMonthDto.setTotalCandidatesForOctober(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(10));
        candidatesPerMonthDto.setTotalCandidatesForNovember(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(11));
        candidatesPerMonthDto.setTotalCandidatesForDecember(candidateVenueJobRepository.findCandidatesPerMonthByAllVenue(12));
        dashboardDto.setTotalCandidatesPerMonth(candidatesPerMonthDto);
        return dashboardDto;
    }

    @Override
    public CandidateVenueJobPaginationDto findListOfCandidatesByFilters(Long venueId, String screeningStatus, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize, String lastName, String level) throws CandidateVenueJobNotFoundException {
        Sort sort = Sort.by("ASC".equals(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        BooleanBuilder predicate = buildCandidatePredicate(screeningStatus, venueId, lastName, level);
        Page<CandidateVenueJob> candidateVenueJobDto = candidateVenueJobRepository.findAll(predicate, pageRequest);
        List<CandidateVenueJobDto> candidateVenueJobDtos = candidateVenueJobDto.stream().map(candidateVenueJobMapper::candidateVenueJobEntityToDto).collect(Collectors.toList());
        var candidateVenueJobPaginationDto = new CandidateVenueJobPaginationDto();
        candidateVenueJobPaginationDto.setCandidateVenueJobDtoList(candidateVenueJobDtos);
        candidateVenueJobPaginationDto.setTotalElements(candidateVenueJobDto.getNumberOfElements());
        candidateVenueJobPaginationDto.setTotalPages(candidateVenueJobDto.getTotalPages());
        return candidateVenueJobPaginationDto;
    }

    private BooleanBuilder buildCandidatePredicate(String screeningStatus, Long venueId, String lastName, String level) {
        var qCandidateVenueJob = QCandidateVenueJob.candidateVenueJob1;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (!screeningStatus.equals("All")) {
            booleanBuilder.and(qCandidateVenueJob.candidate.candidateScreenings.any().screeningStatus.eq(screeningStatus));
        }
        if (venueId != 0) {
            booleanBuilder.and(qCandidateVenueJob.venueJob.venue.venueId.eq(venueId));
        }
        if (!lastName.equals("")) {
            booleanBuilder.and(qCandidateVenueJob.candidate.lastName.contains(lastName));
        }
        if (!level.equals("All")) {
            booleanBuilder.and(qCandidateVenueJob.candidate.currentLevel.eq(level));
        }
        return booleanBuilder;
    }


    private CandidateVenueJobPaginationDto getCandidateVenueJobPaginationDto(Page<CandidateVenueJob> candidateVenueJobs) throws CandidateVenueJobNotFoundException {
        if (candidateVenueJobs == null) {
            throw new CandidateVenueJobNotFoundException(ErrorMessages.NO_CANDIDATE_VENUE_JOB_AVAILABLE.toString());
        } else {
            List<CandidateVenueJobDto> candidateVenueJobDto = candidateVenueJobs.stream().map(candidateVenueJobMapper::candidateVenueJobEntityToDto).collect(Collectors.toList());
            CandidateVenueJobPaginationDto candidateVenueJobPaginationDto = new CandidateVenueJobPaginationDto();
            candidateVenueJobPaginationDto.setCandidateVenueJobDtoList(candidateVenueJobDto);
            candidateVenueJobPaginationDto.setTotalElements(candidateVenueJobs.getNumberOfElements());
            candidateVenueJobPaginationDto.setTotalPages(candidateVenueJobs.getTotalPages());
            return candidateVenueJobPaginationDto;
        }
    }
}
