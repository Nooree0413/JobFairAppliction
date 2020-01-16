package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.entity.Venue;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.exception.VenueNotFoundException;
import com.elca.jobfairmanagementsystem.mapper.VenueMapper;
import com.elca.jobfairmanagementsystem.repository.VenueRepository;
import com.elca.jobfairmanagementsystem.service.VenueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    public VenueServiceImpl(VenueRepository venueRepository, VenueMapper venueMapper) {
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    @Override
    public void saveVenue(VenueDto venueDto) {
        var saveVenueDatas = venueMapper.venueDtoToEntity(venueDto);
        venueRepository.save(saveVenueDatas);
    }

    @Override
    public VenueDto findVenueById(Long venueId) throws VenueNotFoundException {
        Optional<Venue> getVenueById = venueRepository.findById(venueId);
        var venue = getVenueById.orElseThrow(() -> new VenueNotFoundException(ErrorMessages.VENUE_NOT_FOUND.toString()));
        return venueMapper.venueEntityToDto(venue);
    }

    @Override
    public List<VenueDto> findAllVenue() throws VenueNotFoundException {
        List<Venue> getVenueList = venueRepository.findAll();
        if (getVenueList != null) {
            return getVenueList.stream().map(venueMapper::venueEntityToDto).collect(Collectors.toList());
        } else {
            throw new VenueNotFoundException(ErrorMessages.NO_VENUE_AVAILABLE.toString());
        }
    }

    @Override
    public void updateVenue(VenueDto venueDto) throws VenueNotFoundException {
        var getVenueById = findVenueById(venueDto.getVenueId());
        if (getVenueById != null) {
            getVenueById.setVenueName(venueDto.getVenueName());
            getVenueById.setStartDate(venueDto.getStartDate());
            getVenueById.setEndDate(venueDto.getEndDate());
            getVenueById.setActive(venueDto.isActive());
            venueRepository.save(venueMapper.venueDtoToEntity(getVenueById));
        } else {
            throw new VenueNotFoundException(ErrorMessages.VENUE_NOT_FOUND.toString());
        }
    }

    @Override
    public void deleteVenue(Long venueId) throws VenueNotFoundException {
        var getVenue = findVenueById(venueId);
        if (getVenue != null) {
            venueRepository.deleteById(venueId);
        } else {
            throw new VenueNotFoundException(ErrorMessages.VENUE_NOT_FOUND.toString());
        }
    }

    @Override
    public List<VenueDto> findVenueByActive(boolean active) throws VenueNotFoundException {
        List<Venue> getVenueListByActive = venueRepository.findVenueByActive(active);
        if (getVenueListByActive != null) {
            return getVenueListByActive.stream().map(venueMapper::venueEntityToDto).collect(Collectors.toList());
        } else {
            throw new VenueNotFoundException(ErrorMessages.NO_VENUE_AVAILABLE.toString());
        }
    }
}
