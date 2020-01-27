package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

@Data
public class DashboardDto {
    private Integer totalJobsByVenue;
    private CandidatesPerMonthDto totalCandidatesPerMonthByVenue;
    private Integer totalProceedScreeningStatusByVenue;
    private Integer totalApprovedScreeningStatusByVenue;
    private Integer totalRejectedScreeningStatusByVenue;

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
