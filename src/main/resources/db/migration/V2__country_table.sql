CREATE TABLE IF NOT EXISTS COUNTRY (
    id bigserial NOT NULL PRIMARY KEY,
    iso_code varchar(2) NOT NULL,
    name varchar(100) NOT NULL
);
