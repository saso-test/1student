DROP TABLE Student_class;
DROP TABLE Student;
DROP TABLE Student_enrolled_to_class;
commit;
CREATE TABLE IF NOT EXISTS Student_class (
                              class_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                              class_name VARCHAR(20),
                              lecturer_name VARCHAR(20),
                              time VARCHAR(20),
                              semester VARCHAR(20)
);
-- commit;

--http://localhost:8080/h2-console
INSERT INTO Student_class ( class_name, lecturer_name, time, semester)
VALUES
( 'Slovenian', 'Jure Testni1','8.00', 'winter'),
( 'English', 'Miha Testni2','9.00', 'winter'),
( 'Geography', 'Rok Testni3','8.00', 'summer'),
( 'Math', 'Borut Testni4','8.00', 'winter');


CREATE TABLE IF NOT EXISTS Student (
    student_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(30),
    username VARCHAR(100),
    password VARCHAR(100),
    role VARCHAR(10)
);
INSERT INTO Student (student_name, username, password, role)
VALUES
('Mihec Studentko', 'mihas', 'mihapassword', 'USER'),
('Janko Jankovic', 'jankoj', 'jankopassword', 'USER'),
('Mihec Mihajlo', 'mihecm', 'mihecpassword', 'USER'),
('Rokec Pokec', 'rokecp', 'rokecpassword', 'USER'),
('Tine Bine', 'tineb', 'tinepassword', 'USER');

CREATE TABLE IF NOT EXISTS Student_enrolled_to_class (
    student_id INT REFERENCES Student(student_id),
    class_id INT REFERENCES Student_class(class_id),
    PRIMARY KEY (student_id, class_id),
);

commit;
