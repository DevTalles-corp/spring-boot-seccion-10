package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.PersonalInfoDto;
import com.portfolio.my_portfolio_backend.model.PersonalInfo;

public class PersonalInfoMapper {

    public static PersonalInfo toEntity(PersonalInfoDto dto) {
        if (dto == null) {
            return null;
        }
        PersonalInfo entity = new PersonalInfo();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setTitle(dto.getTitle());
        entity.setProfileDescription(dto.getProfileDescription());
        entity.setProfileImageUrl(dto.getProfileImageUrl());
        entity.setYearsOfExperience(dto.getYearsOfExperience());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setLinkedinUrl(dto.getLinkedinUrl());
        entity.setGithubUrl(dto.getGithubUrl());
        return entity;
    }

    public static PersonalInfoDto toDto(PersonalInfo entity) {
        if (entity == null) {
            return null;
        }
        PersonalInfoDto dto = new PersonalInfoDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setTitle(entity.getTitle());
        dto.setProfileDescription(entity.getProfileDescription());
        dto.setProfileImageUrl(entity.getProfileImageUrl());
        dto.setYearsOfExperience(entity.getYearsOfExperience());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setLinkedinUrl(entity.getLinkedinUrl());
        dto.setGithubUrl(entity.getGithubUrl());
        return dto;
    }
}