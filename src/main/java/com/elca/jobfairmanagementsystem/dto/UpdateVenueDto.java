package com.elca.jobfairmanagementsystem.dto;

public class UpdateVenueDto {

    private VenueDto venueDto;

    public UpdateVenueDto() {
        venueDto = new VenueDto();
    }

    private void setVenueName(String venueName) {
        venueDto.setVenueName(venueName);
    }
}
