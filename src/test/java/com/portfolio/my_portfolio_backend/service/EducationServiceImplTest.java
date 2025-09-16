package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EducationServiceImplTest {

    @Mock
    private IEducationRepository educationRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private EducationServiceImpl educationService;


    @Test
    void testFindAllReturnsListOfEducation() {
        // Arrange
        List<Education> mockEducation = Arrays.asList(new Education(), new Education());
        when(educationRepository.findAll()).thenReturn(mockEducation);

        // Act
        List<Education> result = educationService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(educationRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsEducationWhenFound() {
        // Arrange
        Long educationId = 1L;
        Education mockEducation = new Education();
        when(educationRepository.findById(educationId)).thenReturn(Optional.of(mockEducation));

        // Act
        Optional<Education> result = educationService.findById(educationId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockEducation, result.get());
        verify(educationRepository, times(1)).findById(educationId);
    }


    @Test
    void testSaveEducationThrowsExceptionWhenInvalid() {
        // Arrange
        Education invalidEducation = new Education();

        doAnswer(invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("degree", "NotBlank", "El título no puede estar vacío");
            return null;
        }).when(validator).validate(any(Education.class), any(BindingResult.class));

        // Act & Assert
        assertThrows(ValidationException.class, () -> educationService.save(invalidEducation),
                "Debe lanzarse una ValidationException si el objeto no es válido.");
        verify(educationRepository, never()).save(any(Education.class));
    }

    @Test
    void testSaveEducationSavesValidEducation() {
        // Arrange
        Education validEducation = new Education(null, "Ingeniería en Sistemas", "UTN",
                LocalDate.of(2015, 3, 1), LocalDate.of(2020,
                12, 1), "Descripción de la carrera", 1L);

        when(educationRepository.save(any(Education.class))).thenReturn(validEducation);
        doNothing().when(validator).validate(any(Education.class), any(BindingResult.class));

        // Act
        Education savedEducation = educationService.save(validEducation);

        // Assert
        assertNotNull(savedEducation);
        verify(educationRepository, times(1)).save(validEducation);
    }
















}