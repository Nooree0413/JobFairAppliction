package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class VenuePaginationDto {
    List<VenueDto> venueDtoList;
    Integer totalElements;
    Integer totalPages;
}
