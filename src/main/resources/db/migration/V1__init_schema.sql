CREATE TABLE users
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    email              VARCHAR(255) NOT NULL,
    password           VARCHAR(255) NOT NULL,
    nickname           VARCHAR(50)  NOT NULL,
    `role`             VARCHAR(255) NOT NULL,
    enabled            BIT(1)       NOT NULL,
    verification_token VARCHAR(255) NULL,
    created_at         datetime DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_nickname UNIQUE (nickname);
CREATE INDEX idx_users_email ON users (email);
CREATE INDEX idx_users_nickname ON users (nickname);
