package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.entity.Venue;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    Venue venueDtoToEntity (VenueDto venueDto);
    VenueDto venueEntityToDto (Venue venue);
}
