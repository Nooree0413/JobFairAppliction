package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class DashboardDto {
    private Integer totalJobsByVenue;
    private CandidatesPerMonthDto totalCandidatesPerMonth;
    private Integer totalProceedScreeningStatusByVenue;
    private Integer totalApprovedScreeningStatusByVenue;
    private Integer totalRejectedScreeningStatusByVenue;

    private Integer totalCandidatesPerSoftwareEngineerByVenue;
    private Integer totalCandidatesPerBusinessAnalystByVenue;
    private Integer totalCandidatesPerQualityAssuranceByVenue;
    private Integer totalCandidatesPerManagerByVenue;
    private Integer totalCandidatesPerHumanResourceByVenue;
    private Integer totalCandidatesPerArchitectByVenue;

    private Integer totalJobsByAllVenue;
    private Integer totalCandidatesByAllVenue;
    private Integer totalProceedScreeningStatusByAllVenue;
    private Integer totalApprovedScreeningStatusByAllVenue;
    private Integer totalRejectedScreeningStatusByAllVenue;

    private Integer totalCandidatesPerSoftwareEngineerByAllVenue;
    private Integer totalCandidatesPerBusinessAnalystByAllVenue;
    private Integer totalCandidatesPerQualityAssuranceByAllVenue;
    private Integer totalCandidatesPerManagerByAllVenue;
    private Integer totalCandidatesPerHumanResourceByAllVenue;
    private Integer totalCandidatesPerArchitectByAllVenue;
}
