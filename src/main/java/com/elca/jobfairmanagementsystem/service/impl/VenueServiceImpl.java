package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.VenueDto;
import com.elca.jobfairmanagementsystem.entity.Venue;
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

    public VenueServiceImpl(VenueRepository venueRepository,VenueMapper venueMapper){
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    @Override
    public void saveVenue(VenueDto venueDto) {
        var saveVenueDatas = venueMapper.venueDtoToEntity(venueDto);
        venueRepository.save(saveVenueDatas);
    }

    @Override
    public VenueDto searchVenueById(Long venueId) {
        Optional<Venue> getVenueById = venueRepository.findById(venueId);
        var venue = getVenueById.orElse(null);
        if (venue != null){
            return venueMapper.venueEntityToDto(venue);
        } else {
            return null;
        }
    }

    @Override
    public List<VenueDto> searchAllVenue() {
        List<Venue> getVenueList = venueRepository.findAll();
        return getVenueList.stream().map(venueMapper::venueEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void updateVenue(VenueDto venueDto) {
        var getVenueById = searchVenueById(venueDto.getVenueId());
        getVenueById.setVenueName(venueDto.getVenueName());
        getVenueById.setStartDate(venueDto.getStartDate());
        getVenueById.setEndDate(venueDto.getEndDate());
        getVenueById.setStatus(venueDto.isStatus());
        venueRepository.save(venueMapper.venueDtoToEntity(getVenueById));
    }

    @Override
    public void deleteVenue(Long venueId) {
        venueRepository.deleteById(venueId);
    }
}
