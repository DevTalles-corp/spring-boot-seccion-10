package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.SkillDto;
import com.portfolio.my_portfolio_backend.model.Skill;

public class SkillMapper {

    public static Skill toEntity(SkillDto dto){
        if (dto == null){
            return null;
        }

        Skill entity = new Skill();
        entity.setId(dto.getId());
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLevelPercentage(dto.getLevelPercentage());
        entity.setIconClass(dto.getIconClass());
        entity.setPersonalInfoId(dto.getPersonalInfoId());

        return entity;
    }

    public static SkillDto toDto(Skill entity){
        if(entity==null){
            return null;
        }

        SkillDto dto = new SkillDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLevelPercentage(entity.getLevelPercentage());
        dto.setIconClass(entity.getIconClass());
        dto.setPersonalInfoId(entity.getPersonalInfoId());
        return dto;
    }

}
