CREATE TABLE IF NOT EXISTS TRANSFER (
    id bigserial NOT NULL PRIMARY KEY,
    reference varchar(20) NOT NULL,
    sender_country varchar(2) NOT NULL,
    receiver_country varchar(2) NOT NULL
);
