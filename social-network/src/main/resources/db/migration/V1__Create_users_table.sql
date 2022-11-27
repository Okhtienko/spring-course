CREATE TABLE users
(
    id         BIGSERIAL NOT NULL UNIQUE,
    name       VARCHAR   NOT NULL UNIQUE,
    password   VARCHAR   NOT NULL,
    date TIMESTAMP NOT NULL DEFAULT now(),

    PRIMARY KEY (id)
)
