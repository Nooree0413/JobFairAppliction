package com.elca.jobfairmanagementsystem.dto;

import lombok.Data;

import java.util.List;
@Data
public class SkillPaginationDto extends PaginationDto{
    List<SkillDto> skillDtoList;
}
