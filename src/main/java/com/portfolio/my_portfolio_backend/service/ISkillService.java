package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillService {

    List<Skill> findAll();
    Optional<Skill> findById(Long id);
    Skill save(Skill skill);
    void deleteById(Long id);
    List<Skill> findSkillsByPersonalInfoId(Long personalInfoId);
}
