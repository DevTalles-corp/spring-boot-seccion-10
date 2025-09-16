package com.portfolio.my_portfolio_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private Long id;
    private String title;
    private String description;
    private String imageUrl; // URL o ruta a la imagen del proyecto
    private String projectUrl; // URL al proyecto desplegado (si existe) o GitHub
    private Long personalInfoId; // Clave foránea a PersonalInfo
}
