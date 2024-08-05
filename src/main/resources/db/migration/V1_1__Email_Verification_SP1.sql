CREATE TABLE IF NOT EXISTS tb_email_verification
(
    id           integer GENERATED ALWAYS AS IDENTITY NOT NULL,
    code         varchar(12)                          NOT NULL,
    user_id      integer                              NOT NULL,
    created_at   timestamp with time zone             NOT NULL,
    expires_at   timestamp with time zone             NOT NULL,
    confirmed_at timestamp with time zone
);

ALTER TABLE tb_user
    ADD COLUMN is_active bool NOT NULL DEFAULT FALSE;

--- PRIMARY KEYS ---
ALTER TABLE tb_email_verification
    ADD CONSTRAINT pk_email_confirmation PRIMARY KEY (id);

--- FOREIGN KEYS ---
ALTER TABLE tb_email_verification
    ADD CONSTRAINT fk_email_confirmation_user_id FOREIGN KEY (user_id) REFERENCES tb_user;
