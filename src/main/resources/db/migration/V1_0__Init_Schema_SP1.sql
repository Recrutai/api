CREATE TABLE tb_user
(
    id              bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    first_name      varchar(40)                         NOT NULL,
    last_name       varchar(40)                         NOT NULL,
    headline        varchar(150),
    email           varchar(255)                        NOT NULL UNIQUE,
    hashed_password varchar(128)                        NOT NULL,
    location_id     bigint,
    created_at      timestamp with time zone            NOT NULL
);

-- All types of institutions (private, public, association, self-employed)
CREATE TABLE tb_institution
(
    id                   bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    name                 varchar(120)                        NOT NULL,
    type                 varchar(50)                         NOT NULL,
    headline             varchar(150),
    founder_id           bigint                              NOT NULL,
    industry_id          smallint                            NOT NULL,
    company_size         char(2)                             NOT NULL,
    associated_employees integer                             NOT NULL,
    headquarters_id      bigint,
    website              varchar(255),
    about                varchar(2500)                       NOT NULL,
    created_at           timestamp with time zone            NOT NULL,
    deactivated_at       timestamp with time zone
);

-- Every school is a institution with some more information
CREATE TABLE tb_school
(
    institution_id    bigint  NOT NULL,
    school_size       char(2) NOT NULL,
    associated_alumni integer NOT NULL
);

-- Added by a privileged member (owner/admin)
-- if `added_by` is null, member is the owner
CREATE TABLE tb_member
(
    id             bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    user_id        bigint                              NOT NULL,
    institution_id bigint                              NOT NULL,
    role           varchar(50)                         NOT NULL,
    added_by_id    bigint,
    added_at       timestamp with time zone            NOT NULL,
    removed_by_id  bigint,
    removed_at     timestamp with time zone
);

-- Added by the user to his resume as work experience
CREATE TABLE tb_employment
(
    id             bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    user_id        bigint                              NOT NULL,
    institution_id bigint                              NOT NULL,
    title          varchar(100)                        NOT NULL,
    type           varchar(50),
    work_model     varchar(50),
    address_id     bigint,
    description    varchar(2500),
    start_date     integer                             NOT NULL,
    end_date       integer
);

CREATE TABLE tb_vacancy
(
    id              bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    title           varchar(100)                        NOT NULL,
    description     varchar(3850)                       NOT NULL,
    employment_type varchar(50)                         NOT NULL,
    work_model      varchar(50)                         NOT NULL,
    location_id     bigint                              NOT NULL,
    salary          integer                             NOT NULL,
    currency_code   char(3)                             NOT NULL,
    positions       smallint                            NOT NULL,
    applications    integer                             NOT NULL,
    recruiter_id    bigint                              NOT NULL,
    published_by_id bigint                              NOT NULL,
    published_at    timestamp with time zone            NOT NULL,
    closed_by_id    bigint,
    closed_at       timestamp with time zone
);

CREATE TABLE tb_application
(
    id              bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    candidate_id    bigint                              NOT NULL,
    vacancy_id      bigint                              NOT NULL,
    expected_salary integer                             NOT NULL,
    currency_code   char(3)                             NOT NULL,
    applied_at      timestamp with time zone            NOT NULL,
    UNIQUE (candidate_id, vacancy_id)
);

CREATE TABLE tb_interview
(
    id             bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    application_id bigint                              NOT NULL,
    interviewer_id bigint                              NOT NULL,
    title          varchar(100)                        NOT NULL,
    description    varchar(2000),
    scheduled_to   timestamp with time zone            NOT NULL,
    address_id     bigint,
    reunion_url    varchar(255),
    is_remote      boolean                             NOT NULL,
    created_by     bigint                              NOT NULL,
    created_at     timestamp with time zone            NOT NULL
);

CREATE TABLE tb_address
(
    id             bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    street_address varchar(70),
    city           varchar(70)                         NOT NULL,
    state          varchar(70)                         NOT NULL,
    country        varchar(70)                         NOT NULL,
    postal_code    varchar(20),
    latitude       decimal(8, 6),
    longitude      decimal(9, 6)
);

CREATE TABLE tb_industry
(
    id   smallint GENERATED ALWAYS AS IDENTITY NOT NULL,
    name varchar(100)                          NOT NULL UNIQUE
);

CREATE TABLE tb_institution_address
(
    address_id     bigint NOT NULL,
    institution_id bigint NOT NULL
);


--- PRIMARY KEYS ---
ALTER TABLE tb_user
    ADD CONSTRAINT pk_user PRIMARY KEY (id);

ALTER TABLE tb_institution
    ADD CONSTRAINT pk_institution PRIMARY KEY (id);

ALTER TABLE tb_school
    ADD CONSTRAINT pk_school PRIMARY KEY (institution_id);

ALTER TABLE tb_member
    ADD CONSTRAINT pk_member PRIMARY KEY (id);

ALTER TABLE tb_employment
    ADD CONSTRAINT pk_employment PRIMARY KEY (id);

ALTER TABLE tb_vacancy
    ADD CONSTRAINT pk_vacancy PRIMARY KEY (id);

ALTER TABLE tb_application
    ADD CONSTRAINT pk_application PRIMARY KEY (id);

ALTER TABLE tb_interview
    ADD CONSTRAINT pk_interview PRIMARY KEY (id);

ALTER TABLE tb_address
    ADD CONSTRAINT pk_address PRIMARY KEY (id);

ALTER TABLE tb_industry
    ADD CONSTRAINT pk_industry PRIMARY KEY (id);

ALTER TABLE tb_institution_address
    ADD CONSTRAINT pk_institution_address PRIMARY KEY (address_id);


--- FOREIGN KEYS ---
ALTER TABLE tb_user
    ADD CONSTRAINT fk_user_location_id
        FOREIGN KEY (location_id) REFERENCES tb_address;

ALTER TABLE tb_institution
    ADD CONSTRAINT fk_institution_founder_id
        FOREIGN KEY (founder_id) REFERENCES tb_user;

ALTER TABLE tb_institution
    ADD CONSTRAINT fk_institution_industry_id
        FOREIGN KEY (industry_id) REFERENCES tb_industry;

ALTER TABLE tb_institution
    ADD CONSTRAINT fk_institution_headquarters_id
        FOREIGN KEY (headquarters_id) REFERENCES tb_address;

ALTER TABLE tb_school
    ADD CONSTRAINT fk_school_institution_id
        FOREIGN KEY (institution_id) REFERENCES tb_institution;

ALTER TABLE tb_member
    ADD CONSTRAINT fk_member_user_id
        FOREIGN KEY (user_id) REFERENCES tb_user;

ALTER TABLE tb_member
    ADD CONSTRAINT fk_member_institution_id
        FOREIGN KEY (institution_id) REFERENCES tb_institution;

ALTER TABLE tb_member
    ADD CONSTRAINT fk_member_added_by
        FOREIGN KEY (added_by_id) REFERENCES tb_member;

ALTER TABLE tb_member
    ADD CONSTRAINT fk_member_removed_by
        FOREIGN KEY (removed_by_id) REFERENCES tb_member;

ALTER TABLE tb_employment
    ADD CONSTRAINT fk_employment_user_id
        FOREIGN KEY (user_id) REFERENCES tb_user;

ALTER TABLE tb_employment
    ADD CONSTRAINT fk_employment_institution_id
        FOREIGN KEY (institution_id) REFERENCES tb_institution;

ALTER TABLE tb_employment
    ADD CONSTRAINT fk_employment_address_id
        FOREIGN KEY (address_id) REFERENCES tb_address;

ALTER TABLE tb_vacancy
    ADD CONSTRAINT fk_vacancy_location_id
        FOREIGN KEY (location_id) REFERENCES tb_address;

ALTER TABLE tb_vacancy
    ADD CONSTRAINT fk_vacancy_recruiter_id
        FOREIGN KEY (recruiter_id) REFERENCES tb_member;

ALTER TABLE tb_vacancy
    ADD CONSTRAINT fk_vacancy_published_by_id
        FOREIGN KEY (published_by_id) REFERENCES tb_member;

ALTER TABLE tb_vacancy
    ADD CONSTRAINT fk_vacancy_closed_by_id
        FOREIGN KEY (closed_by_id) REFERENCES tb_member;

ALTER TABLE tb_application
    ADD CONSTRAINT fk_application_user_id
        FOREIGN KEY (candidate_id) REFERENCES tb_user;

ALTER TABLE tb_application
    ADD CONSTRAINT fk_application_vacancy_id
        FOREIGN KEY (vacancy_id) REFERENCES tb_vacancy;

ALTER TABLE tb_interview
    ADD CONSTRAINT fk_interview_application_id
        FOREIGN KEY (application_id) REFERENCES tb_application;

ALTER TABLE tb_interview
    ADD CONSTRAINT fk_interview_interviewer_id
        FOREIGN KEY (interviewer_id) REFERENCES tb_member;

ALTER TABLE tb_interview
    ADD CONSTRAINT fk_interview_address_id
        FOREIGN KEY (address_id) REFERENCES tb_address;

ALTER TABLE tb_interview
    ADD CONSTRAINT fk_interview_created_by
        FOREIGN KEY (created_by) REFERENCES tb_member;

ALTER TABLE tb_institution_address
    ADD CONSTRAINT fk_institution_address_institution_id
        FOREIGN KEY (institution_id) REFERENCES tb_institution;

ALTER TABLE tb_institution_address
    ADD CONSTRAINT fk_institution_address_address_id
        FOREIGN KEY (address_id) REFERENCES tb_address;