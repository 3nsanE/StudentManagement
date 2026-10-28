CREATE TABLE students
(
    id     INT PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(50) NOT NULL,
    age    INT         NOT NULL,
    course VARCHAR(50) NOT NULL,
    marks  DOUBLE      NOT NULL
);
