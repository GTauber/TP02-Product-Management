CREATE TABLE course
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    version    BIGINT                NULL,
    name       VARCHAR(255)          NULL,
    created_at VARCHAR(255)          NULL,
    updated_at VARCHAR(255)          NULL,
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE student
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    version    BIGINT                NULL,
    name       VARCHAR(255)          NULL,
    email      VARCHAR(255)          NULL,
    created_at datetime              NULL,
    updated_at datetime              NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE TABLE student_course
(
    course_id  BIGINT NOT NULL,
    student_id BIGINT NOT NULL,
    CONSTRAINT pk_student_course PRIMARY KEY (course_id, student_id)
);

ALTER TABLE student_course
    ADD CONSTRAINT fk_sc_on_course FOREIGN KEY (course_id) REFERENCES course (id);

ALTER TABLE student_course
    ADD CONSTRAINT fk_sc_on_student FOREIGN KEY (student_id) REFERENCES student (id);