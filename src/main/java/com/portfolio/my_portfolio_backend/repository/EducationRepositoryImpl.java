package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EducationRepositoryImpl implements IEducationRepository{

    private final JdbcTemplate jdbcTemplate;

    public EducationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Education> educationRowMapper = (rs, rowNum) -> {
        Education education = new Education();

        education.setId(rs.getLong("id"));
        education.setDegree(rs.getString("degree"));
        education.setInstitution(rs.getString("institution"));
        education.setStartDate(rs.getObject("start_date", LocalDate.class)); // Mapear a LocalDate
        education.setEndDate(rs.getObject("end_date", LocalDate.class));     // Mapear a LocalDate (puede ser nulo)
        education.setDescription(rs.getString("description"));
        education.setPersonalInfoId(rs.getLong("personal_info_id"));

        return education;
    };

    @Override
    public Education save(Education education) {
        if(education.getId()==null){
            String sql = "INSERT INTO educations (degree, institution, start_date, end_date, description, personal_info_id) VALUES (?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});

                ps.setString(1, education.getDegree());
                ps.setString(2, education.getInstitution());
                ps.setObject(3, education.getStartDate()); // Usar setObject para LocalDate
                ps.setObject(4, education.getEndDate());   // Usar setObject para LocalDate (admite null)
                ps.setString(5, education.getDescription());
                ps.setLong(6, education.getPersonalInfoId());

                return ps;

            }, keyHolder);

            education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        }else{

            String sql = "UPDATE educations SET degree = ?, institution = ?, start_date = ?, end_date = ?, " +
                    "description = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    education.getDegree(),
                    education.getInstitution(),
                    education.getStartDate(),
                    education.getEndDate(),
                    education.getDescription(),
                    education.getPersonalInfoId(),
                    education.getId());
        }
        return education;
    }

    @Override
    public Optional<Education> findById(Long id) {
        String sql = "SELECT id, degree, institution, start_date, end_date, description, " +
                "personal_info_id FROM educations WHERE id = ?";
        try{
            return Optional.of(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Education> findAll() {
        String sql = "SELECT id, degree, institution, start_date, end_date, description, personal_info_id FROM educations";
        return jdbcTemplate.query(sql,educationRowMapper );
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM educations WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Education> findByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT id, degree, institution, start_date, end_date, description, " +
                "personal_info_id FROM educations WHERE personal_info_id = ?";
        return jdbcTemplate.query(sql, educationRowMapper, personalInfoId);
    }
}
