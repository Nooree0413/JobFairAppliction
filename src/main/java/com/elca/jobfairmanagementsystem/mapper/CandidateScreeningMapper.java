package com.elca.jobfairmanagementsystem.mapper;

import com.elca.jobfairmanagementsystem.dto.CandidateScreeningDto;
import com.elca.jobfairmanagementsystem.entity.CandidateScreening;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 *
 * @author ghr
 */
@Mapper(componentModel = "spring")
public interface CandidateScreeningMapper {
    @Mapping(target = "candidate.candidateId", source = "candidateScreeningDto.candidateId")
    @Mapping(target = "venueJob.venueJobId", source = "candidateScreeningDto.venueJobId")
    CandidateScreening candidateScreeningDtoToEntity (CandidateScreeningDto candidateScreeningDto);

    @Mapping(target = "candidateId", source = "candidateScreening.candidate.candidateId")
    @Mapping(target = "venueJobId", source = "candidateScreening.venueJob.venueJobId")
    CandidateScreeningDto candidateScreeningEntityToDto (CandidateScreening candidateScreening);
}
