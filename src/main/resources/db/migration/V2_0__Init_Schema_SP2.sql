CREATE TABLE tb_course
(
    id              bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    user_id         bigint                              NOT NULL,
    school_id       bigint                              NOT NULL,
    name            varchar(120)                        NOT NULL,
    workload_hours  integer                             NOT NULL,
    completion_date integer                             NOT NULL,
    description     varchar(2000)
);


--- PRIMARY KEYS ---
ALTER TABLE tb_course
    ADD CONSTRAINT pk_course PRIMARY KEY (id);


--- FOREIGN KEYS ---
ALTER TABLE tb_course
    ADD CONSTRAINT fk_course_user_id
        FOREIGN KEY (user_id) REFERENCES tb_user;

ALTER TABLE tb_course
    ADD CONSTRAINT fk_course_school_id
        FOREIGN KEY (school_id) REFERENCES tb_school;