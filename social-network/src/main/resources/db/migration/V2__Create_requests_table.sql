CREATE TABLE requests
(
    id           BIGSERIAL NOT NULL UNIQUE,
    sender_id    BIGINT  NOT NULL,
    recipient_id BIGINT  NOT NULL,
    date   TIMESTAMP NOT NULL DEFAULT now(),

    PRIMARY KEY (id)
)
