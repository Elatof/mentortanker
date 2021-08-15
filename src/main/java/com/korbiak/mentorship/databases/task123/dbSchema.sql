DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS subject;
DROP TABLE IF EXISTS tutor;
DROP TABLE IF EXISTS phone_number;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS primary_skill;

CREATE TABLE primary_skill
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL UNIQUE
);

CREATE TABLE student
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(32) NOT NULL,
    surname          VARCHAR(32) NOT NULL,
    date_of_birth    VARCHAR(32) NOT NULL,
    primary_skill_id INT         NOT NULL,
    created_datetime TIMESTAMP   NOT NULL,
    updated_datetime TIMESTAMP   NOT NULL,
    UNIQUE (name, surname),

    CONSTRAINT fk_primary_skill
        FOREIGN KEY (primary_skill_id)
            REFERENCES primary_skill (id)
);

CREATE TABLE phone_number
(
    id         SERIAL PRIMARY KEY,
    number     VARCHAR(10) NOT NULL,
    student_id INT         NOT NULL,
    UNIQUE (number),

    CONSTRAINT fk_student_id
        FOREIGN KEY (student_id)
            REFERENCES student (id)
);

CREATE TABLE tutor
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    UNIQUE (name, surname)
);

CREATE TABLE subject
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(32) NOT NULL,
    tutor_id INT         NOT NULL,

    CONSTRAINT fk_tutor
        FOREIGN KEY (tutor_id)
            REFERENCES tutor (id)
);


CREATE TABLE result
(
    id         SERIAL PRIMARY KEY,
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    mark       INT CHECK (mark > 0 and mark < 100),

    CONSTRAINT fk_student
        FOREIGN KEY (student_id)
            REFERENCES student (id),
    CONSTRAINT fk_subject
        FOREIGN KEY (subject_id)
            REFERENCES subject (id)
);