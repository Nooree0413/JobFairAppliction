package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;
@Data
public class VenueJobPaginationDto {
    List<VenueJobDto> venueJobDtoList;
    Integer totalElements;
    Integer totalPages;
}
