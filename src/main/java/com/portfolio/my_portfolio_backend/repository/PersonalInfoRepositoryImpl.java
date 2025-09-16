package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository{

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, numRow) ->{
        PersonalInfo info = new PersonalInfo();
        info.setId(rs.getLong("id"));
        info.setFirstName(rs.getString("first_name"));
        info.setLastName(rs.getString("last_name"));
        info.setTitle(rs.getString("title"));
        info.setProfileDescription(rs.getString("profile_description"));
        info.setProfileImageUrl(rs.getString("profile_image_url"));
        info.setYearsOfExperience(rs.getObject("years_of_experience", Integer.class)); // Usar getObject para nulos
        info.setEmail(rs.getString("email"));
        info.setPhone(rs.getString("phone"));
        info.setLinkedinUrl(rs.getString("linkedin_url"));
        info.setGithubUrl(rs.getString("github_url"));
        return info;
    };

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        if(personalInfo.getId()==null){
            String sql = "INSERT INTO personal_info (first_name, last_name, title, profile_description, " +
                    "profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update( connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String []{"id"});
                ps.setString(1, personalInfo.getFirstName());
                ps.setString(2, personalInfo.getLastName());
                ps.setString(3, personalInfo.getTitle());
                ps.setString(4, personalInfo.getProfileDescription());
                ps.setString(5, personalInfo.getProfileImageUrl());
                // Para tipos primitivos que pueden ser nulos en la DB (INT), usa setNull si el valor es null
                if (personalInfo.getYearsOfExperience() != null) {
                    ps.setInt(6, personalInfo.getYearsOfExperience());
                } else {
                    ps.setNull(6, java.sql.Types.INTEGER);
                }
                ps.setString(7, personalInfo.getEmail());
                ps.setString(8, personalInfo.getPhone());
                ps.setString(9, personalInfo.getLinkedinUrl());
                ps.setString(10, personalInfo.getGithubUrl());
                return ps;
                    }, keyHolder);

            personalInfo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        }else {
            String sql = "UPDATE personal_info SET first_name=?, last_name=?, title=?, profile_description=?, " +
                    "profile_image_url=?, years_of_experience=?, email=?, phone=?, linkedin_url=?, github_url=? " +
                    "WHERE id=?";

            jdbcTemplate.update(sql,
                    personalInfo.getFirstName(),
                    personalInfo.getLastName(),
                    personalInfo.getTitle(),
                    personalInfo.getProfileDescription(),
                    personalInfo.getProfileImageUrl(),
                    personalInfo.getYearsOfExperience(),
                    personalInfo.getEmail(),
                    personalInfo.getPhone(),
                    personalInfo.getLinkedinUrl(),
                    personalInfo.getGithubUrl(),
                    personalInfo.getId()
            );
        }
        return personalInfo;
    }

//    @Override
//    public Optional<PersonalInfo> findById(Long id) {
//        String sql = "Select * From personal_info Where id = ?";
//        List<PersonalInfo> infos = jdbcTemplate.query(sql, personalInfoRowMapper)
//        return infos.stream().findFirst();
//    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        String sql = "Select * From personal_info Where id = ?";
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "Select * From personal_info";
        return jdbcTemplate.query(sql, personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete From personal_info Where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
