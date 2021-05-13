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
