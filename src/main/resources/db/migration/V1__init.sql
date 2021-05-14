CREATE TABLE IF NOT EXISTS app_user (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        active INT NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        first_name VARCHAR(255) NOT NULL,
                        last_name VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        role VARCHAR(255) NOT NULL
);

INSERT INTO app_user (active,email,first_name,last_name, password, role) values (1,'scheduleadmin@miu.edu','Schedule', 'Admin', '$2a$10$Q0AhVP0h49r9E3O5yVq86uiXQFoQZCXSRGnEzdNNA2GQq9Bb/hdMS', 'ADMIN');
INSERT INTO app_user (active,email,first_name,last_name, password, role)
values (1,'student.one@miu.edu','Student', 'One', '$2a$10$Q0AhVP0h49r9E3O5yVq86uiXQFoQZCXSRGnEzdNNA2GQq9Bb/hdMS', 'STUDENT');
INSERT INTO app_user (active,email,first_name,last_name, password, role)
values (1,'student.two@miu.edu','Student', 'Two', '$2a$10$Q0AhVP0h49r9E3O5yVq86uiXQFoQZCXSRGnEzdNNA2GQq9Bb/hdMS', 'STUDENT');
INSERT INTO app_user (active,email,first_name,last_name, password, role)
values (1,'faculty.one@miu.edu','Faculty', 'One', '$2a$10$Q0AhVP0h49r9E3O5yVq86uiXQFoQZCXSRGnEzdNNA2GQq9Bb/hdMS', 'FACULTY');
INSERT INTO app_user (active,email,first_name,last_name, password, role)
values (1,'faculty.two@miu.edu','Faculty', 'Two', '$2a$10$Q0AhVP0h49r9E3O5yVq86uiXQFoQZCXSRGnEzdNNA2GQq9Bb/hdMS', 'FACULTY');

# create entry table

CREATE TABLE IF NOT EXISTS `entry` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `capacity` int(11) DEFAULT NULL,
                         `date` date DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# insert into entry table
INSERT INTO `entry` (`capacity`, `date`, `name`) VALUES (150, '2021-02-01', 'February_2021');

# create block table
CREATE TABLE IF NOT EXISTS `block` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `date` date DEFAULT NULL,
                         `end_date` date DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `start_date` date DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# insert into blocks table
INSERT INTO `block` (`date`, `end_date`, `name`, `start_date`) VALUES ('2021-05-01', '2021-06-01', 'May_Block', '2021-05-01');
INSERT INTO `block` (`date`, `end_date`, `name`, `start_date`) VALUES ('2021-06-01', '2021-07-01', 'June_Block', '2021-06-01');
INSERT INTO `block` (`date`, `end_date`, `name`, `start_date`) VALUES ('2021-07-01', '2021-08-01', 'July_Block', '2021-07-01');
INSERT INTO `block` (`date`, `end_date`, `name`, `start_date`) VALUES ('2021-08-01', '2021-09-01', 'August_Block', '2021-08-01');

CREATE TABLE IF NOT EXISTS `course` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `code` varchar(255) DEFAULT NULL,
                          `description` varchar(255) DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# insert into courses table
INSERT INTO `course` (`code`, `description`, `name`) VALUES ('CS425', 'Software Engineering', 'Software Engineering');
INSERT INTO `course` (`code`, `description`, `name`) VALUES ('CS435', 'Algorithms', 'Algorithms');
INSERT INTO `course` (`code`, `description`, `name`) VALUES ('CS590', 'Software Architecture', 'Software Architecture');
INSERT INTO `course` (`code`, `description`, `name`) VALUES ('CS472', 'Web Application Programming', 'Web Application Programming');

# create faculty table
CREATE TABLE IF NOT EXISTS `faculty` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `biography` varchar(255) NOT NULL,
                           `end_date` date DEFAULT NULL,
                           `staff_id` bigint(20) NOT NULL,
                           `start_date` date DEFAULT NULL,
                           `user_id` bigint(20) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UK_staff_id` (`staff_id`),
                           KEY `FK_app_user_faculty` (`user_id`),
                           CONSTRAINT `FK_app_user_faculty` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# insert into faculty table
INSERT INTO `faculty` (`biography`, `staff_id`, `user_id`, `end_date`, `start_date`) VALUES ('Algorithm and compilers researcher', 20398,
                                                                                             (SELECT u.id FROM `app_user` u WHERE u.`email` = "faculty.one@miu.edu"), '2021-11-30', '2021-05-25');
INSERT INTO `faculty` (`biography`, `staff_id`, `user_id`, `end_date`, `start_date`) VALUES ('Machine learning expert', 30568,
                                                                                             (SELECT u.id FROM `app_user` u WHERE u.`email` = "faculty.two@miu.edu"), '2021-11-30', '2021-05-25');

# create student table
CREATE TABLE IF NOT EXISTS `student` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `is_active` bit(1) NOT NULL,
                           `has_registered_courses` bit(1) NOT NULL,
                           `registration_number` decimal(19,2) NOT NULL,
                           `track` varchar(255) NOT NULL,
                           `creator_id` bigint(20) DEFAULT NULL,
                           `entry_id` bigint(20) DEFAULT NULL,
                           `user_id` bigint(20) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UK_registration_number` (`registration_number`),
                           KEY `FK_creator_id_app_user_student` (`creator_id`),
                           KEY `FK_entry_student` (`entry_id`),
                           KEY `FK_app_user_student` (`user_id`),
                           CONSTRAINT `FK_entry_student` FOREIGN KEY (`entry_id`) REFERENCES `entry` (`id`),
                           CONSTRAINT `FK_creator_id_app_user_student` FOREIGN KEY (`creator_id`) REFERENCES `app_user` (`id`),
                           CONSTRAINT `FK_app_user_student` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# insert into students table
INSERT INTO `student` (`is_active`, `registration_number`, `track`, `user_id`, `has_registered_courses`, `creator_id`, `entry_id`)
VALUES (1, 612363, "FPP", (SELECT u.id FROM `app_user` u WHERE u.`email` = "student.one@miu.edu"), 0,
        (SELECT u.id FROM `app_user` u WHERE u.`email` = "scheduleadmin@miu.edu"),
        (SELECT e.id FROM entry  e WHERE e.`name` = "January_2021"));

INSERT INTO `student` (`is_active`, `registration_number`, `track`, `user_id`, `has_registered_courses`, `creator_id`, `entry_id`)
VALUES (1, 612310, "MPP", (SELECT u.id FROM `app_user` u WHERE u.`email` = "student.two@miu.edu"), 0,
        (SELECT u.id FROM `app_user` u WHERE u.`email` = "scheduleadmin@miu.edu"),
        (SELECT e.id FROM entry  e WHERE e.`name` = "January_2021"));

# create table faculty course
CREATE TABLE `faculty_course` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `block_id` bigint(20) NOT NULL,
                                  `course_id` bigint(20) NOT NULL,
                                  `faculty_id` bigint(20) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FK_block_faculty_course` (`block_id`),
                                  KEY `FK_course_faculty_course` (`course_id`),
                                  KEY `FK_facult_faculty_course` (`faculty_id`),
                                  CONSTRAINT `FK_facult_faculty_course` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`),
                                  CONSTRAINT `FK_course_faculty_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
                                  CONSTRAINT `FK_block_faculty_course` FOREIGN KEY (`block_id`) REFERENCES `block` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

# insert faculty course table
INSERT INTO `faculty_course` (`block_id`, `course_id`, `faculty_id`)
VALUES ((SELECT b.`id` FROM `block` b ORDER BY b.`start_date` LIMIT 0,1),
        (SELECT c.`id` FROM `course` c ORDER BY c.`code` LIMIT 0,1),
    (SELECT f.`id` FROM `faculty` f ORDER BY f.`staff_id` LIMIT 0,1));