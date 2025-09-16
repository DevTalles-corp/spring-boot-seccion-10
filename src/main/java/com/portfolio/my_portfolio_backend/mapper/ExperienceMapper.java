package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.ExperienceDto;
import com.portfolio.my_portfolio_backend.model.Experience;

public class ExperienceMapper {

    public static Experience toEntity(ExperienceDto dto) {
        if (dto == null) {
            return null;
        }
        Experience entity = new Experience();
        entity.setId(dto.getId());
        entity.setJobTitle(dto.getJobTitle());
        entity.setCompanyName(dto.getCompanyName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setDescription(dto.getDescription());
        entity.setPersonalInfoId(dto.getPersonalInfoId());
        return entity;
    }

    public static ExperienceDto toDto(Experience entity) {
        if (entity == null) {
            return null;
        }
        ExperienceDto dto = new ExperienceDto();
        dto.setId(entity.getId());
        dto.setJobTitle(entity.getJobTitle());
        dto.setCompanyName(entity.getCompanyName());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setDescription(entity.getDescription());
        dto.setPersonalInfoId(entity.getPersonalInfoId());
        return dto;
    }
}