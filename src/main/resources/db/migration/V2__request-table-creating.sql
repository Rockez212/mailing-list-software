CREATE SEQUENCE request_id_seq START 1;


create table requests(
    id BIGINT PRIMARY KEY DEFAULT nextVal('request_id_seq'),
    to_email varchar(255) not null,
    text varchar(2000) not null,
    status VARCHAR(50) NOT NULL CHECK (status IN ('COMPLETED', 'NOT_COMPLETED')),
    created_at timestamp not null,
    done_at timestamp  null
);