INSERT INTO `user` (create_at, email, enabled, first_name, last_name, password, social_id, username) VALUES(NULL, 'admin@admin.cl', true, 'Admin', 'admin', '$2a$10$m7IuhSw2vUrZWUkVq/M/tu8BQBOIwrNWWhQh04CcbWMjdFPWVa6Yy', '172132332', 'admin');
INSERT INTO `user` (create_at, email, enabled, first_name, last_name, password, social_id, username) VALUES(NULL, 'user@admin.cl', true, 'User', 'User', '$2a$10$m7IuhSw2vUrZWUkVq/M/tu8BQBOIwrNWWhQh04CcbWMjdFPWVa6Yy', '168946430', 'user');

INSERT INTO `role` (name) VALUES('ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES(1, 1);
