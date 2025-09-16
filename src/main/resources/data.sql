INSERT INTO personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url) VALUES
('Juan', 'Perez', 'Full Stack Developer', 'Apasionado por el desarrollo web con experiencia en Java, Spring Boot y React. Disfruto construyendo soluciones robustas y escalables.', 'img/dev-gabriel.png', 5, 'juan.perez@example.com', '+5491112345678', 'https://linkedin.com/in/juanperez', 'https://github.com/juanperez');

INSERT INTO skills (name, level_percentage, icon_class, personal_info_id) VALUES
('Java', 90, 'img/logos/java.png', 1),
('GitHub', 85, 'img/logos/github.png', 1),
('PostgreSQL', 80, 'img/logos/servidor-sql.png', 1),
('HTML', 95, 'img/logos/html-5.png', 1),
('CSS', 90, 'img/logos/css-3.png', 1),
('JavaScript', 75, 'img/logos/js.png', 1),
('C', 70, 'img/logos/logotipo-de-c-.png', 1);

INSERT INTO educations (degree, institution, start_date, end_date, description, personal_info_id) VALUES
('Ingeniería en Sistemas', 'Universidad XYZ', '2015-03-01', '2020-12-15', 'Especialización en desarrollo de software y bases de datos.', 1),
('Curso de Spring Boot Avanzado', 'Plataforma ABC', '2021-01-10', '2021-06-30', 'Profundización en microservicios y seguridad.', 1);

INSERT INTO experiences (job_title, company_name, start_date, end_date, description, personal_info_id) VALUES
('Desarrollador Full Stack Senior', 'Tech Solutions S.A.', '2022-01-01', NULL, 'Desarrollo y mantenimiento de aplicaciones empresariales. Liderazgo técnico de equipo de 3 personas.', 1),
('Desarrollador Backend Junior', 'Innovatech Labs', '2020-09-01', '2021-12-31', 'Participación en el desarrollo de APIs RESTful y optimización de bases de datos.', 1);

INSERT INTO projects (title, description, image_url, project_url, personal_info_id) VALUES
('Portfolio Personal', 'Un portafolio web para mostrar mis habilidades y proyectos.', 'img/projects/project2.jpg', 'https://github.com/myusername/my-portfolio', 1),
('Aplicación de E-commerce', 'Plataforma de comercio electrónico con carrito de compras y pasarela de pago.', 'img/projects/project1.jpg', 'https://github.com/myusername/ecommerce-app', 1);