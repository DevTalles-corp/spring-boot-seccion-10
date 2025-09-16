package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.repository.IExperienceRepository;
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
public class ExperienceServiceImplTest {

    @Mock
    private IExperienceRepository experienceRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private ExperienceServiceImpl experienceService;

    @Test
    void testFindAllReturnsListOfExperience() {
        // Arrange
        List<Experience> mockExperience = Arrays.asList(new Experience(), new Experience());
        when(experienceRepository.findAll()).thenReturn(mockExperience);

        // Act
        List<Experience> result = experienceService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(experienceRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsExperienceWhenFound() {
        // Arrange
        Long experienceId = 1L;
        Experience mockExperience = new Experience();
        when(experienceRepository.findById(experienceId)).thenReturn(Optional.of(mockExperience));

        // Act
        Optional<Experience> result = experienceService.findById(experienceId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockExperience, result.get());
        verify(experienceRepository, times(1)).findById(experienceId);
    }

    @Test
    void testSaveExperienceThrowsExceptionWhenInvalid() {
        // Arrange
        Experience invalidExperience = new Experience();

        doAnswer(invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("jobTitle", "NotBlank", "El título del puesto no puede estar vacío");
            return null;
        }).when(validator).validate(any(Experience.class), any(BindingResult.class));

        // Act & Assert
        assertThrows(ValidationException.class, () -> experienceService.save(invalidExperience),
                "Debe lanzarse una ValidationException si el objeto no es válido.");
        verify(experienceRepository, never()).save(any(Experience.class));
    }

    @Test
    void testSaveExperienceSavesValidExperience() {
        // Arrange
        Experience validExperience = new Experience(null, "Desarrollador Java", "Globant", LocalDate.of(2021, 1, 1), LocalDate.of(2024, 1, 1), "Desarrollo de APIs", 1L);
        when(experienceRepository.save(any(Experience.class))).thenReturn(validExperience);
        doNothing().when(validator).validate(any(Experience.class), any(BindingResult.class));

        // Act
        Experience savedExperience = experienceService.save(validExperience);

        // Assert
        assertNotNull(savedExperience);
        verify(experienceRepository, times(1)).save(validExperience);
    }
}