create table if not exists users (
    user_id BIGSERIAL PRIMARY KEY,
    username varchar(255) not null,
    password varchar(255) not null
);

alter table if exists users
    add constraint UK_USERS_USERNAME unique (username);

create table if not exists trails (
    trail_id BIGSERIAL PRIMARY KEY,
    title varchar(255),
    update_time timestamp,
    users_user_id int8
);

alter table if exists trails
    add constraint FK_TRAILS_USERS_USER_ID
    foreign key (users_user_id) references users;

create table if not exists roles (
    role_id BIGSERIAL PRIMARY KEY,
    name varchar(255) not null
);

alter table if exists roles
    add constraint UK_ROLES_NAME unique (name);

create table if not exists users_roles (
    user_id int8 not null,
    role_id int8 not null
);

alter table if exists users_roles
    add constraint FK_UR_ROLE_ID
    FOREIGN KEY (role_id) REFERENCES roles ON DELETE CASCADE ON UPDATE CASCADE;

alter table if exists users_roles
    add constraint FK_UR_USER_ID
    FOREIGN KEY (user_id) REFERENCES users ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO roles (name)
    VALUES ('ROLE_USER'), ('ROLE_ADMIN')
    ON CONFLICT (name) DO NOTHING;