CREATE TABLE files
(
    id           SERIAL PRIMARY KEY,
    file_name    VARCHAR(32) NOT NULL,
    file_content OID         NOT NULL,
    expire_time  DATE        NOT NULL
)