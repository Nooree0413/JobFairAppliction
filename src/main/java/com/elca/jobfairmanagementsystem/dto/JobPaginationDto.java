package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class JobPaginationDto extends PaginationDto {
    List<JobDto> jobDtoList;
}
