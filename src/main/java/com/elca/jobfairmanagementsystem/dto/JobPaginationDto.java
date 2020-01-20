package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;
@Data
public class JobPaginationDto {
    List<JobDto> jobDtoList;
    Integer totalElements;
    Integer totalPages;
}
