INSERT INTO `user` (create_at, email, enabled, first_name, last_name, password, social_id, username) VALUES(NULL, 'admin@admin', true, 'Administrador', 'Sistema', '$2a$10$m7IuhSw2vUrZWUkVq/M/tu8BQBOIwrNWWhQh04CcbWMjdFPWVa6Yy', '0000000000', 'admin');

INSERT INTO `role` (name) VALUES('ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES(1, 1);
