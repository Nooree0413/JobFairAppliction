package com.elca.jobfairmanagementsystem.service.impl;

import com.elca.jobfairmanagementsystem.dto.CandidateSkillDto;
import com.elca.jobfairmanagementsystem.entity.CandidateSkill;
import com.elca.jobfairmanagementsystem.exception.CandidateSkillNotFoundException;
import com.elca.jobfairmanagementsystem.exception.ErrorMessages;
import com.elca.jobfairmanagementsystem.mapper.CandidateSkillMapper;
import com.elca.jobfairmanagementsystem.repository.CandidateSkillRepository;
import com.elca.jobfairmanagementsystem.service.CandidateSkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CandidateSkillImpl implements CandidateSkillService {
    private final CandidateSkillMapper candidateSkillMapper;
    private final CandidateSkillRepository candidateSkillRepository;

    public CandidateSkillImpl(CandidateSkillMapper candidateSkillMapper, CandidateSkillRepository candidateSkillRepository) {
        this.candidateSkillMapper = candidateSkillMapper;
        this.candidateSkillRepository = candidateSkillRepository;
    }

    @Override
    public List<CandidateSkillDto> findAllCandidateSkills() throws CandidateSkillNotFoundException {
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findAll();
        if (candidateSkills.size() != 0) {
            return candidateSkills.stream()
                    .map(candidateSkillMapper::candidateSkillEntityToDto)
                    .collect(Collectors.toList());
        } else {
            throw new CandidateSkillNotFoundException(ErrorMessages.NO_CANDIDATE_SKILL_AVAILABLE.toString());
        }
    }

    @Override
    public void saveCandidateSkill(List<CandidateSkillDto> candidateSkillDto) {
        candidateSkillDto.forEach(System.out::println);
        candidateSkillRepository.saveAll(candidateSkillDto.stream().filter(CandidateSkillDto::isChecked).map(candidateSkillMapper::candidateSkillDtoToEntity).collect(Collectors.toList()));
    }

    @Override
    public void deleteCandidateSkill(Long candidateSkillId) throws CandidateSkillNotFoundException {
        var candidateSkill = findCandidateSkillById(candidateSkillId);
        if (candidateSkill != null) {
            candidateSkillRepository.deleteById(candidateSkillId);
        } else {
            throw new CandidateSkillNotFoundException(ErrorMessages.CANDIDATE_SKILL_NOT_FOUND.toString());
        }
    }

    @Override
    public void updateCandidateSkill(CandidateSkillDto candidateSkillDto) throws CandidateSkillNotFoundException {
        var getCandidateSkillById = findCandidateSkillById(candidateSkillDto.getCandidateSkillId());
        if (getCandidateSkillById != null) {
            getCandidateSkillById.setCandidateId(candidateSkillDto.getCandidateId());
            getCandidateSkillById.setSkill(candidateSkillDto.getSkill());
            candidateSkillRepository.save(candidateSkillMapper.candidateSkillDtoToEntity(getCandidateSkillById));
        } else {
            throw new CandidateSkillNotFoundException(ErrorMessages.CANDIDATE_SKILL_NOT_FOUND.toString());
        }
    }

    @Override
    public CandidateSkillDto findCandidateSkillById(Long candidateSkillId) throws CandidateSkillNotFoundException {
        Optional<CandidateSkill> getCandidateSkill = candidateSkillRepository.findById(candidateSkillId);
        var candidateSkill = getCandidateSkill.orElseThrow(() -> new CandidateSkillNotFoundException(ErrorMessages.CANDIDATE_SKILL_NOT_FOUND.toString()));
        return candidateSkillMapper.candidateSkillEntityToDto(candidateSkill);
    }
}
