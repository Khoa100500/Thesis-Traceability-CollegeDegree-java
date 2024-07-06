CREATE SCHEMA `student_tracker`;
USE student_tracker;

CREATE TABLE users(
	user_id INT PRIMARY KEY AUTO_INCREMENT NOT NULL ,
    username VARCHAR(255) NOT NULL UNIQUE,
    user_fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
	user_password VARCHAR(255) NOT NULL
);

CREATE TABLE roles(
	role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(10)
);

CREATE TABLE user_role(
	user_id INT NOT NULL,
    role_id INT NOT NULL,
	CONSTRAINT FK_USER FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT FK_ROLE FOREIGN KEY(role_id) REFERENCES roles(role_id),
    PRIMARY KEY(user_id, role_id)
);

CREATE TABLE signatures(
	sign_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	is_verified_against_root BOOLEAN NOT NULL,
    `issuer` VARCHAR(255) NOT NULL,
    `subject` VARCHAR(255) NOT NULL,
    valid_from VARCHAR(255) NOT NULL,
    valid_to VARCHAR(255) NOT NULL,
    validity_now VARCHAR(35) NOT NULL,
    validity_on_sign VARCHAR(55) NOT NULL,
    contact_info VARCHAR(255) NOT NULL,
	digest_algorithm VARCHAR(255) NOT NULL,
	encryption_algorithm VARCHAR(255) NOT NULL,
	field_on_page TINYINT NOT NULL,
	filter_subtype VARCHAR(255),
	integrity BOOLEAN NOT NULL,
	is_annotations_allowed BOOLEAN NOT NULL,
	is_fill_in_allowed BOOLEAN NOT NULL,
	is_signature_invisible BOOLEAN NOT NULL,
	is_time_stamp_verified BOOLEAN NOT NULL,
	location VARCHAR(255) NOT NULL,
	reason VARCHAR(255) NOT NULL,
	revision VARCHAR(255) NOT NULL,
	signature_covers_whole_document BOOLEAN NOT NULL,
	signature_type VARCHAR(255) NOT NULL,
	signed_on DATE NOT NULL,
	signer_alternative_name VARCHAR(255) NOT NULL,
	signer_name VARCHAR(255) NOT NULL,
	`time_stamp` VARCHAR(255) NOT NULL,
	time_stamp_service VARCHAR(255) NOT NULL
);

CREATE TABLE report(
	report_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    sign_id INT NOT NULL,
    course_id VARCHAR(10) NOT NULL,
    course_name VARCHAR(50) NOT NULL,
    group_id VARCHAR(5) NOT NULL,
    hpUNIT VARCHAR(10) NOT NULL,
    lecturer_id VARCHAR(10) NOT NULL,
    lecturer_name VARCHAR(255) NOT NULL,
    time_posted DATETIME,
    CONSTRAINT FK_REPORT_USER FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT FK_REPORT_SIGN FOREIGN KEY(sign_id) REFERENCES signatures(sign_id)
);

CREATE TABLE student_scores(
	student_score_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    report_id INT NOT NULL,
    student_id VARCHAR(11) NOT NULL,
    student_name VARCHAR(255) NOT NULL,
    birthday DATE,
    class_id VARCHAR(20),
    inclass INT,
    midterm INT,
    final INT,
    `description` VARCHAR(255),
    CONSTRAINT FK_SCORE_REPORT FOREIGN KEY(report_id) REFERENCES report(report_id)
);

CREATE USER 'user'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON student_tracker.* TO 'user'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO `student_tracker`.`roles` (`role_id`, `role_name`) VALUES ('1', 'USER');
INSERT INTO `student_tracker`.`roles` (`role_id`, `role_name`) VALUES ('2', 'ADMIN');