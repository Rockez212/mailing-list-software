CREATE SEQUENCE users_id_seq START 1;


create table users
(
    id            BIGINT PRIMARY KEY DEFAULT nextval('users_id_seq'),
    username      varchar(255)        not null,
    email         varchar(255) UNIQUE not null,
    password      varchar(255) unique not null,
    refresh_token varchar(255)
);