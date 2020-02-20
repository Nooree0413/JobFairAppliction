package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class CandidateVenueJobPaginationDto extends PaginationDto {
    List<CandidateVenueJobDto> candidateVenueJobDtoList;
}
