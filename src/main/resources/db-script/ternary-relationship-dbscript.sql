DROP TABLE take;
DROP TABLE registered_by;
DROP TABLE course_module;
DROP TABLE batch;
DROP TABLE course;
DROP TABLE module;
DROP TABLE user;
DROP TABLE contact;
DROP TABLE student;


CREATE TABLE course
(
    code VARCHAR(15) PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);



CREATE TABLE module(
    code   VARCHAR(15) PRIMARY KEY,
    name   VARCHAR(200) NOT NULL,
    credit VARCHAR(10)  NOT NULL,
    type   ENUM('Optional','Mandatory')  NOT NULL
);

ALTER TABLE module MODIFY COLUMN  credit DECIMAL(4,2);


CREATE TABLE user
(
    username VARCHAR(100) PRIMARY KEY,
    name     VARCHAR(200) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE student
(
    nic     VARCHAR(10) PRIMARY KEY,
    name    VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL
);

CREATE TABLE contact
(
    contact     VARCHAR(15) PRIMARY KEY,
    student_nic VARCHAR(10) NOT NULL,
    CONSTRAINT fk_student FOREIGN KEY (student_nic) REFERENCES student (nic)
);
# 1000000
CREATE TABLE batch
(
    batch_id    VARCHAR(10) PRIMARY KEY,
    duration    VARCHAR(10) NOT NULL,
    course_code VARCHAR(15) NOT NULL,
    CONSTRAINT fk_course FOREIGN KEY (course_code) REFERENCES course (code)
);

ALTER TABLE batch ADD COLUMN fee DECIMAL(10,2);

CREATE TABLE registered_by
(
    username    VARCHAR(100) NOT NULL ,
    student_nic VARCHAR(10) NOT NULL  ,
    batch_id    VARCHAR(10) NOT NULL   ,
    CONSTRAINT pk_registered_by PRIMARY KEY (username, student_nic, batch_id),
    CONSTRAINT fk_user FOREIGN KEY (username) REFERENCES user (username),
    CONSTRAINT fk_student_reg FOREIGN KEY (student_nic) REFERENCES student (nic),
    CONSTRAINT fk_batch_reg FOREIGN KEY (batch_id) REFERENCES batch (batch_id),
    date        DATE NOT NULL
);

CREATE TABLE course_module(
    course_code VARCHAR(15) NOT NULL ,
    module_code VARCHAR(15) NOT NULL ,
    CONSTRAINT pk_course_module PRIMARY KEY (course_code,module_code),
    CONSTRAINT fk_course_cm FOREIGN KEY (course_code) REFERENCES course(code),
    CONSTRAINT fk_module_cm FOREIGN KEY (module_code) REFERENCES module(code)
);

CREATE TABLE take(
    module_code VARCHAR(15) NOT NULL ,
    batch_id VARCHAR(10) NOT NULL ,
    student_nic VARCHAR(10) NOT NULL ,
    CONSTRAINT pk_take PRIMARY KEY (module_code,batch_id,student_nic),
    CONSTRAINT fk_module FOREIGN KEY (module_code) REFERENCES module(code),
    CONSTRAINT fk_batch FOREIGN KEY (batch_id) REFERENCES batch(batch_id),
    CONSTRAINT fk_student_take FOREIGN KEY (student_nic) REFERENCES student(nic)
);