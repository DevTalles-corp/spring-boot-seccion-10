DROP TABLE IF EXISTS experiences;
DROP TABLE IF EXISTS educations;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS personal_info;

CREATE TABLE personal_info (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    title VARCHAR(255) NOT NULL,
    profile_description TEXT NOT NULL,
    profile_image_url VARCHAR(500),
    years_of_experience INT,
    email VARCHAR(255),
    phone VARCHAR(50),
    linkedin_url VARCHAR(500),
    github_url VARCHAR(500)
);

CREATE TABLE skills (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    level_percentage INT,
    icon_class VARCHAR(100),
    personal_info_id BIGINT NOT NULL,
    CONSTRAINT fk_skill_personal_info
        FOREIGN KEY (personal_info_id)
        REFERENCES personal_info (id)
        ON DELETE CASCADE
);

CREATE TABLE educations (
    id SERIAL PRIMARY KEY,
    degree VARCHAR(255) NOT NULL,
    institution VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    description TEXT,
    personal_info_id BIGINT NOT NULL,
    CONSTRAINT fk_education_personal_info
        FOREIGN KEY (personal_info_id)
        REFERENCES personal_info (id)
        ON DELETE CASCADE
);

CREATE TABLE experiences (
    id SERIAL PRIMARY KEY,
    job_title VARCHAR(255) NOT NULL,
    company_name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    description TEXT,
    personal_info_id BIGINT NOT NULL,
    CONSTRAINT fk_experience_personal_info
        FOREIGN KEY (personal_info_id)
        REFERENCES personal_info (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS projects (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(255),
    project_url VARCHAR(255),
    personal_info_id INT,
    FOREIGN KEY (personal_info_id) REFERENCES personal_info(id)
);















