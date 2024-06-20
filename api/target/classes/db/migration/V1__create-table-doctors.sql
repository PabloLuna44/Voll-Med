CREATE TABLE doctors(
     id BIGINT NOT NULL  AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL ,
    email VARCHAR(100) NOT NULL UNIQUE ,
    document VARCHAR(100) NOT NULL UNIQUE ,
    specialty VARCHAR(100) NOT NULL ,
    street VARCHAR(100) NOT NULL ,
    state VARCHAR(100) NOT NULL ,
    city VARCHAR(100) NOT NULL ,
    district VARCHAR(100) NOT NULL ,
    number VARCHAR(20) ,
    complement VARCHAR(100),

    PRIMARY KEY (id)
);