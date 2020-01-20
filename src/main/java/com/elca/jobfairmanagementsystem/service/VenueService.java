package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.dto.VenuePaginationDto;
import com.elca.jobfairmanagementsystem.exception.VenueNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VenueService {
    void saveVenue(VenueDto venueDto);

    VenueDto findVenueById(Long venueId) throws VenueNotFoundException;

    VenuePaginationDto findAllVenue(Pageable pageable) throws VenueNotFoundException;

    void updateVenue(VenueDto venueDto) throws VenueNotFoundException;

    void deleteVenue(Long venueId) throws VenueNotFoundException;

    List<VenueDto> findVenueByActive(boolean active) throws VenueNotFoundException;
}
