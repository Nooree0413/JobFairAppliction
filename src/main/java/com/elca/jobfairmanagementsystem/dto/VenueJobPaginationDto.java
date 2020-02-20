package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class VenueJobPaginationDto extends PaginationDto {
    List<VenueJobDto> venueJobDtoList;
}
