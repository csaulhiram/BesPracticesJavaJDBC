CREATE DATABASE test;
USE test;

CREATE TABLE personas(
    id_persona int(11)  NOT NULL,
    nombre VARCHAR(35) NOT NULL,
    apellido VARCHAR(35) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    PRIMARY KEY(id_persona)
);
 
