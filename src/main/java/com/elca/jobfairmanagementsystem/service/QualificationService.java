package com.elca.jobfairmanagementsystem.service;

import com.elca.jobfairmanagementsystem.dto.QualificationDto;
import java.util.List;

public interface QualificationService {

    List<QualificationDto> findAllQualifications();

}
