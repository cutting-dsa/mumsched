# insert entry for february
INSERT INTO `entry` (`capacity`, `date`, `name`) VALUES (150, '2021-02-01', 'February_2021');

# insert blocks
INSERT INTO `block` (`date`, `end_date`, `name`, `start_date`) VALUES ('2021-05-01', '2021-06-01', 'May_Block', '2021-05-01');
INSERT INTO `block` (`date`, `end_date`, `name`, `start_date`) VALUES ('2021-06-01', '2021-07-01', 'June_Block', '2021-06-01');
INSERT INTO `block` (`date`, `end_date`, `name`, `start_date`) VALUES ('2021-07-01', '2021-08-01', 'July_Block', '2021-07-01');
INSERT INTO `block` (`date`, `end_date`, `name`, `start_date`) VALUES ('2021-08-01', '2021-09-01', 'August_Block', '2021-08-01');

# insert courses
INSERT INTO `course` (`code`, `description`, `name`) VALUES ('CS425', 'Software Engineering', 'Software Engineering');
INSERT INTO `course` (`code`, `description`, `name`) VALUES ('CS435', 'Algorithms', 'Algorithms');
INSERT INTO `course` (`code`, `description`, `name`) VALUES ('CS590', 'Software Architecture', 'Software Architecture');
INSERT INTO `course` (`code`, `description`, `name`) VALUES ('CS472', 'Web Application Programming', 'Web Application Programming');

# insert faculty
INSERT INTO `faculty` (`biography`, `staff_id`, `user_id`, `end_date`, `start_date`) VALUES ('Algorithm research and comiplers', 20398,
                                                                                             (SELECT u.id FROM `app_user` u WHERE u.`email` = "faculty.one@miu.edu"), '2021-11-30', '2021-05-25');
INSERT INTO `faculty` (`biography`, `staff_id`, `user_id`, `end_date`, `start_date`) VALUES ('Algorithm research and comiplers', 30568,
                                                                                             (SELECT u.id FROM `app_user` u WHERE u.`email` = "faculty.two@miu.edu"), '2021-11-30', '2021-05-25');

# insert students
INSERT INTO `student` (`is_active`, `registration_number`, `user_id`, `has_registered_courses`, `creator_id`, `entry_id`)
VALUES (1, 612363, (SELECT u.id FROM `app_user` u WHERE u.`email` = "student.one@miu.edu"), 0,
        (SELECT u.id FROM `app_user` u WHERE u.`email` = "scheduleadmin@miu.edu"),
        (SELECT e.id FROM entry  e WHERE e.`name` = "January_2021"));

INSERT INTO `student` (`is_active`, `registration_number`, `user_id`, `has_registered_courses`, `creator_id`, `entry_id`)
VALUES (1, 612310, (SELECT u.id FROM `app_user` u WHERE u.`email` = "student.two@miu.edu"), 0,
        (SELECT u.id FROM `app_user` u WHERE u.`email` = "scheduleadmin@miu.edu"),
        (SELECT e.id FROM entry  e WHERE e.`name` = "January_2021"));