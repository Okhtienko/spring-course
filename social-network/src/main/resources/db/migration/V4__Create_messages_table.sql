CREATE TABLE messages
(
    id               BIGSERIAL NOT NULL UNIQUE,
    first_friend_id  BIGINT  NOT NULL,
    second_friend_id BIGINT  NOT NULL,
    message_text     TEXT,
    date   TIMESTAMP NOT NULL DEFAULT now(),

    PRIMARY KEY (id)
)
