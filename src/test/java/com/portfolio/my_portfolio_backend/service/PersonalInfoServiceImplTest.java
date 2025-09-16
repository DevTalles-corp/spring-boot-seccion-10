package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.repository.IPersonalInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonalInfoServiceImplTest {

    @Mock
    private IPersonalInfoRepository personalInfoRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private PersonalInfoServiceImpl personalInfoService;

    @Test
    void testFindAllReturnsListOfPersonalInfo() {
        // Arrange
        List<PersonalInfo> mockPersonalInfo = Arrays.asList(new PersonalInfo(), new PersonalInfo());
        when(personalInfoRepository.findAll()).thenReturn(mockPersonalInfo);

        // Act
        List<PersonalInfo> result = personalInfoService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(personalInfoRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsPersonalInfoWhenFound() {
        // Arrange
        Long personalInfoId = 1L;
        PersonalInfo mockPersonalInfo = new PersonalInfo();
        when(personalInfoRepository.findById(personalInfoId)).thenReturn(Optional.of(mockPersonalInfo));

        // Act
        Optional<PersonalInfo> result = personalInfoService.findById(personalInfoId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(mockPersonalInfo, result.get());
        verify(personalInfoRepository, times(1)).findById(personalInfoId);
    }

    @Test
    void testSavePersonalInfoThrowsExceptionWhenInvalid() {
        // Arrange
        PersonalInfo invalidPersonalInfo = new PersonalInfo();

        doAnswer(invocationOnMock -> {
            BindingResult result = invocationOnMock.getArgument(1);
            result.rejectValue("firstName", "NotBlank", "El nombre no puede estar vacío");
            return null;
        }).when(validator).validate(any(PersonalInfo.class), any(BindingResult.class));

        // Act & Assert
        assertThrows(ValidationException.class, () -> personalInfoService.save(invalidPersonalInfo),
                "Debe lanzarse una ValidationException si el objeto no es válido.");
        verify(personalInfoRepository, never()).save(any(PersonalInfo.class));
    }

    @Test
    void testSavePersonalInfoSavesValidPersonalInfo() {
        // Arrange
        PersonalInfo validPersonalInfo = new PersonalInfo(null, "John", "Doe", "Desarrollador", "Descripción del perfil", "https://url.com/perfil.jpg", 5, "john.doe@email.com", "123456789", "https://linkedin.com/in/johndoe", "https://github.com/johndoe");
        when(personalInfoRepository.save(any(PersonalInfo.class))).thenReturn(validPersonalInfo);
        doNothing().when(validator).validate(any(PersonalInfo.class), any(BindingResult.class));

        // Act
        PersonalInfo savedPersonalInfo = personalInfoService.save(validPersonalInfo);

        // Assert
        assertNotNull(savedPersonalInfo);
        verify(personalInfoRepository, times(1)).save(validPersonalInfo);
    }
}