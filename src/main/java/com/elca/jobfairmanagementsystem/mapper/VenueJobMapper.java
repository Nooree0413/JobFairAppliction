package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.VenueJobDto;
import com.elca.jobfairmanagementsystem.entity.VenueJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueJobMapper {
    @Mapping(target = "venue.venueId", source = "venueJobDto.venue.venueId")
    @Mapping(target = "job.jobId", source = "venueJobDto.job.jobId")
    VenueJob venueJobDtoToEntity(VenueJobDto venueJobDto);

    @Mapping(target = "venue.venueId", source = "venueJob.venue.venueId")
    @Mapping(target = "job.jobId", source = "venueJob.job.jobId")
    VenueJobDto venueJobEntityToDto(VenueJob venueJob);
}
