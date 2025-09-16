package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.EducationDto;
import com.portfolio.my_portfolio_backend.model.Education;

public class EducationMapper {

    public static Education toEntity(EducationDto dto) {
        if (dto == null) {
            return null;
        }
        Education entity = new Education();
        entity.setId(dto.getId());
        entity.setDegree(dto.getDegree());
        entity.setInstitution(dto.getInstitution());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setDescription(dto.getDescription());
        entity.setPersonalInfoId(dto.getPersonalInfoId());
        return entity;
    }

    public static EducationDto toDto(Education entity) {
        if (entity == null) {
            return null;
        }
        EducationDto dto = new EducationDto();
        dto.setId(entity.getId());
        dto.setDegree(entity.getDegree());
        dto.setInstitution(entity.getInstitution());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setDescription(entity.getDescription());
        dto.setPersonalInfoId(entity.getPersonalInfoId());
        return dto;
    }
}