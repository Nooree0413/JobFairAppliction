package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import com.elca.jobfairmanagementsystem.entity.Qualification;
import com.elca.jobfairmanagementsystem.mapper.QualificationMapper;
import com.elca.jobfairmanagementsystem.repository.QualificationRepository;
import com.elca.jobfairmanagementsystem.service.QualificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public Qualification saveQualification(QualificationDto qualificationDto) {
        return null;
    }

    @Override
    public void deleteQualification(Long qualificationId) {

    }

    @Override
    public void updateQualification(QualificationDto qualificationDto) {

    }
}
