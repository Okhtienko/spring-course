CREATE TABLE tokens
(
    id          BIGSERIAL NOT NULL UNIQUE,
    token       VARCHAR   NOT NULL UNIQUE,
    user_id     BIGINT  NOT NULL,

    PRIMARY KEY (id)
)
