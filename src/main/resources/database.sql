CREATE DATABASE db_task_manager ENCODING 'UTF-8';

CREATE TABLE users
(
    user_id  BIGSERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE tasks
(
    task_id       BIGSERIAL PRIMARY KEY,
    title         VARCHAR(50) NOT NULL,
    description   TEXT        NOT NULL,
    created_at    DATE        NOT NULL,
    completion_at DATE        NOT NULL,
    frozen_day    DATE,
    status        VARCHAR(20) NOT NULL,
    fk_user       BIGSERIAL REFERENCES users (user_id)
);