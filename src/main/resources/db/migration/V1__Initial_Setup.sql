-- This file is used to create the table in the database
-- BIGSERIAL is used to auto increment the id and create the sequence for us

CREATE TABLE customers(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    age INT NOT NULL
);