CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       refresh_token VARCHAR(255)
);
