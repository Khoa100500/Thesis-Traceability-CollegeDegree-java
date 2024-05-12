CREATE SCHEMA `student_tracker` ;
USE student_tracker;

CREATE TABLE users(
	user_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE,
    user_fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL
);

CREATE TABLE roles(
	role_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

CREATE TABLE user_role(
	user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY(user_id, role_id),
    CONSTRAINT FK_User FOREIGN KEY(user_id) REFERENCES users(user_id),
    CONSTRAINT FK_Role FOREIGN KEY(role_id) REFERENCES roles(role_id)
);

INSERT INTO `student_tracker`.`roles` (`role_id`, `role_name`) VALUES ('1', 'ADMIN');
INSERT INTO `student_tracker`.`roles` (`role_id`, `role_name`) VALUES ('2', 'USER');
