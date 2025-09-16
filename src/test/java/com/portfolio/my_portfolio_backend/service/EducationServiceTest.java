package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EducationServiceTest {
    @Autowired
    private IEducationService educationService;
    @Autowired
    private IEducationRepository educationRepository;

    @Test
    void testSaveValidEducation() {
        Education validEducation =
                new Education(null, "Ingeniería de Software", "Universidad de XYZ",
                        LocalDate.of(2015, 3, 1), LocalDate.of(2020, 12,
                        15), "Descripción", 1L);
        Education savedEducation = educationService.save(validEducation);

        assertNotNull(savedEducation.getId(), "El objeto guardado debe tener un ID asignado");
        assertNotNull(educationRepository.findById(savedEducation.getId()).orElse(null),
                "El objeto guardado debe existir en la base de datos");
    }

    @Test
    void testSaveInvalidInstitution() {
        Education invalidEducation = new Education(null, "Ingeniería de Software", "",
                LocalDate.of(2015, 3, 1), LocalDate.of(2020, 12, 15),
                "Descripción", 1L);

        assertThrows(ValidationException.class, () -> educationService.save(invalidEducation),
                "Debe lanzarse una ValidationException cuando el nombre de la institución está vacío");
    }
}
