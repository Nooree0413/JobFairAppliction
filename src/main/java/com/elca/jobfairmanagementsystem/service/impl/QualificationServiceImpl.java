package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import com.elca.jobfairmanagementsystem.mapper.QualificationMapper;
import com.elca.jobfairmanagementsystem.repository.QualificationRepository;
import com.elca.jobfairmanagementsystem.service.QualificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepo;
    private final QualificationMapper qualificationMapper;

    public QualificationServiceImpl(QualificationRepository qualificationRepo, QualificationMapper qualificationMapper) {
        this.qualificationRepo = qualificationRepo;
        this.qualificationMapper = qualificationMapper;
    }

    @Override
    public List<QualificationDto> searchAllQualifications() {

        List<Qualification> qualifications = qualificationRepo.findAll();

        if (qualifications.size() != 0) {

            return qualifications.stream()
                    .map(qualificationMapper::qualificationEntityToDto)
                    .collect(Collectors.toList());
        } else
            return null;
    }


    @Override
    public void saveQualification(QualificationDto qualificationDto) {

        Qualification qualification = qualificationMapper.qualificationDtoToEntity(qualificationDto);
        qualificationRepo.save(qualification);
    }

    @Override
    public void deleteQualification(Long qualificationId) {

        qualificationRepo.deleteById(qualificationId);

    }

    @Override
    public void updateQualification(QualificationDto qualificationDto) {

    }


    @Override
    public QualificationDto findByQualificationId(Long qualificationId) {

        Optional<Qualification> optionalQualification = qualificationRepo.findById(qualificationId);

        var qualification = optionalQualification.orElse(null);

        return qualificationMapper.qualificationEntityToDto(qualification);
    }


}
