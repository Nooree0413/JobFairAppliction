package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
public class CandidatePaginationDto extends PaginationDto{
    List<CandidateDto> candidateDtoList;
}
