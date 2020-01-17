package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;
@Data
public class CandidateVenueJobPaginationDto {
    List<CandidateVenueJobDto> candidateVenueJobDtoList;
    Integer totalElements;
    Integer totalPages;
}
