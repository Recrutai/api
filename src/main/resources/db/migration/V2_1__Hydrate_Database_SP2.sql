INSERT INTO tb_employment_type(name)
VALUES ('Contrato'),
       ('Freelance'),
       ('Estágio'),
       ('Full-time'),
       ('Part-time'),
       ('Terceirizado');

INSERT INTO tb_institution_size(lower_bound, upper_bound)
VALUES (1, 1),
       (2, 10),
       (11, 50),
       (51, 200),
       (201, 1000),
       (1001, 5000),
       (5001, 10000),
       (10000, NULL);

INSERT INTO tb_industry(name)
VALUES ('Tecnologia'),
       ('Pesquisa e Inovação'),
       ('Administração');