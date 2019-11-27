package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.exception.VenueNotFoundException;

import java.util.List;

public interface VenueService {
    void saveVenue(VenueDto venueDto);

    VenueDto findVenueById(Long venueId) throws VenueNotFoundException;

    List<VenueDto> findAllVenue() throws VenueNotFoundException;

    void updateVenue(VenueDto venueDto) throws VenueNotFoundException;

    void deleteVenue(Long venueId) throws VenueNotFoundException;
}
