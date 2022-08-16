INSERT INTO users (user_id, username, password)
VALUES (2, 'Admin', '$2a$10$CEDPpbDUqWWIaJSGDrxLS.eNNCE1PSeF2R9IUtWrjTDx9pJWWnONK');

INSERT INTO users (user_id, username, password)
VALUES (3, 'Test', '$2a$10$CEDPpbDUqWWIaJSGDrxLS.eNNCE1PSeF2R9IUtWrjTDx9pJWWnONK');

INSERT INTO users_roles (user_id, role_id)
VALUES (2, 2);

INSERT INTO users_roles (user_id, role_id)
VALUES (3, 1);

INSERT INTO trails (trail_id, title, update_time, users_user_id)
VALUES (2,'english 3h', '2022-08-16 15:59:10.902', 3);