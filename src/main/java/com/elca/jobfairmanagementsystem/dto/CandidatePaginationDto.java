package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;
@Data
public class CandidatePaginationDto {
    List<CandidateDto> candidateDtoList;
    Integer totalElements;
    Integer totalPages;
}
