CREATE TABLE users(

    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE ,
    password VARCHAR(300) NOT NULL,

    PRIMARY KEY (id)
);