INSERT INTO users (user_id, username, password)
VALUES (1, 'Admin', '$2a$10$CEDPpbDUqWWIaJSGDrxLS.eNNCE1PSeF2R9IUtWrjTDx9pJWWnONK');

INSERT INTO users (user_id, username, password)
VALUES (2, 'Test', '$2a$10$CEDPpbDUqWWIaJSGDrxLS.eNNCE1PSeF2R9IUtWrjTDx9pJWWnONK');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2);

INSERT INTO users_roles (user_id, role_id)
VALUES (2, 1);

INSERT INTO trails (trail_id, title, update_time, users_user_id)
VALUES (1,'english 3h', '2022-08-16 15:59:10.902', 2);