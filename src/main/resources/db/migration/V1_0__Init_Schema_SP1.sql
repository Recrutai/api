CREATE TABLE tb_user
(
    id              integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    first_name      varchar(40)                          NOT NULL,
    last_name       varchar(40)                          NOT NULL,
    email           varchar(255)                         NOT NULL,
    hashed_password varchar(128)                         NOT NULL,
    created_at      timestamp with time zone             NOT NULL
);

CREATE TABLE tb_institution
(
    id          integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    owner_id    integer                              NOT NULL,
    name        varchar(120)                         NOT NULL,
    type        varchar(70)                          NOT NULL,
    industry    varchar(120),
    address_id  integer                              NOT NULL UNIQUE,
    employees   integer                              NOT NULL DEFAULT 0,
    alumni      integer                              NOT NULL DEFAULT 0,
    description varchar(2500)                        NOT NULL,
    website     varchar(2000),
    created_at  timestamp with time zone             NOT NULL
);

CREATE TABLE tb_employment
(
    id             integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    user_id        integer                              NOT NULL,
    institution_id integer                              NOT NULL,
    title          varchar(120)                         NOT NULL,
    type           varchar(70)                          NOT NULL,
    description    varchar(2500),
    start_date     integer                              NOT NULL,
    end_date       integer                              NOT NULL
);

CREATE TABLE tb_member
(
    id             integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    user_id        integer                              NOT NULL,
    institution_id integer                              NOT NULL,
    role           varchar(50)                          NOT NULL,
    added_by       integer,
    added_at       timestamp with time zone             NOT NULL,
    removed_by     integer,
    removed_at     timestamp with time zone
);

CREATE TABLE tb_vacancy
(
    id           integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    recruiter_id integer                              NOT NULL,
    title        varchar(120)                         NOT NULL,
    description  varchar(2500)                        NOT NULL,
    work_model   varchar(60)                          NOT NULL,
    avg_salary   integer                              NOT NULL,
    positions    smallint                             NOT NULL,
    applications integer                              NOT NULL DEFAULT 0,
    published_by integer                              NOT NULL,
    published_at timestamp with time zone             NOT NULL,
    closed_by    integer,
    closed_at    timestamp with time zone
);

CREATE TABLE tb_application
(
    id              integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    candidate_id    integer                              NOT NULL,
    vacancy_id      integer                              NOT NULL,
    expected_salary integer                              NOT NULL,
    status          varchar(50)                          NOT NULL,
    applied_at      timestamp with time zone             NOT NULL
);

CREATE TABLE tb_interview
(
    id                       integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    interviewer_id           integer                              NOT NULL,
    candidate_application_id integer                              NOT NULL,
    title                    varchar(120)                         NOT NULL,
    description              varchar(2500),
    scheduled_to             timestamp with time zone             NOT NULL,
    model                    varchar(50)                          NOT NULL,
    reunion_url              varchar(2000),
    address_id               integer,
    created_by               integer                              NOT NULL,
    created_at               timestamp with time zone             NOT NULL
);

CREATE TABLE tb_address
(
    id             integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    street_address varchar(70),
    city           varchar(70)                          NOT NULL,
    state          varchar(70)                          NOT NULL,
    country        varchar(70)                          NOT NULL,
    postal_code    varchar(20),
    latitude       decimal(8, 6),
    longitude      decimal(9, 6)
);


-- PRIMARY KEYS --
ALTER TABLE tb_user
    ADD CONSTRAINT pk_user PRIMARY KEY (id);

ALTER TABLE tb_institution
    ADD CONSTRAINT pk_institution PRIMARY KEY (id);

ALTER TABLE tb_employment
    ADD CONSTRAINT pk_employment PRIMARY KEY (id);

ALTER TABLE tb_member
    ADD CONSTRAINT pk_member PRIMARY KEY (id);

ALTER TABLE tb_vacancy
    ADD CONSTRAINT pk_vacancy PRIMARY KEY (id);

ALTER TABLE tb_application
    ADD CONSTRAINT pk_application PRIMARY KEY (id);

ALTER TABLE tb_interview
    ADD CONSTRAINT pk_interview PRIMARY KEY (id);

ALTER TABLE tb_address
    ADD CONSTRAINT pk_address PRIMARY KEY (id);


-- FOREIGN KEYS --
ALTER TABLE tb_institution
    ADD CONSTRAINT fk_institution_owner_id
        FOREIGN KEY (owner_id) REFERENCES tb_user;

ALTER TABLE tb_institution
    ADD CONSTRAINT fk_institution_address_id
        FOREIGN KEY (address_id) REFERENCES tb_address;

ALTER TABLE tb_employment
    ADD CONSTRAINT fk_employment_user_id
        FOREIGN KEY (user_id) REFERENCES tb_user;

ALTER TABLE tb_employment
    ADD CONSTRAINT fk_employment_institution_id
        FOREIGN KEY (institution_id) REFERENCES tb_institution;

ALTER TABLE tb_member
    ADD CONSTRAINT fk_member_user_id
        FOREIGN KEY (user_id) REFERENCES tb_member;

ALTER TABLE tb_member
    ADD CONSTRAINT fk_member_institution_id
        FOREIGN KEY (institution_id) REFERENCES tb_institution;

ALTER TABLE tb_member
    ADD CONSTRAINT fk_member_added_by
        FOREIGN KEY (added_by) REFERENCES tb_member;

ALTER TABLE tb_member
    ADD CONSTRAINT fk_member_removed_by
        FOREIGN KEY (removed_by) REFERENCES tb_member;

ALTER TABLE tb_vacancy
    ADD CONSTRAINT fk_vacancy_recruiter_id
        FOREIGN KEY (recruiter_id) REFERENCES tb_member;

ALTER TABLE tb_vacancy
    ADD CONSTRAINT fk_vacancy_published_by
        FOREIGN KEY (published_by) REFERENCES tb_member;

ALTER TABLE tb_vacancy
    ADD CONSTRAINT fk_vacancy_closed_by
        FOREIGN KEY (closed_by) REFERENCES tb_member;

ALTER TABLE tb_application
    ADD CONSTRAINT fk_application_vacancy_id
        FOREIGN KEY (vacancy_id) REFERENCES tb_vacancy;

ALTER TABLE tb_application
    ADD CONSTRAINT fk_application_candidate_id
        FOREIGN KEY (candidate_id) REFERENCES tb_user;

ALTER TABLE tb_interview
    ADD CONSTRAINT fk_interview_interviewer_id
        FOREIGN KEY (interviewer_id) REFERENCES tb_member;

ALTER TABLE tb_interview
    ADD CONSTRAINT fk_interview_candidate_application_id
        FOREIGN KEY (candidate_application_id) REFERENCES tb_application;

ALTER TABLE tb_interview
    ADD CONSTRAINT fk_interview_address_id
        FOREIGN KEY (address_id) REFERENCES tb_address;

ALTER TABLE tb_interview
    ADD CONSTRAINT fk_interview_created_by
        FOREIGN KEY (created_by) REFERENCES tb_member;
