create sequence hibernate_sequence start 1 increment 1;

create table users (
    user_id int8 not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (user_id)
);

alter table if exists users
    add constraint UK_USERS_USERNAME unique (username);

create table roles (
    role_id int8 not null,
    name varchar(255) not null,
    primary key (role_id)
);

alter table if exists roles
    add constraint UK_ROLES_NAME unique (name);

create table trails (
    trail_id int8 not null,
    title varchar(255),
    update_time timestamp,
    users int8,
    primary key (trail_id)
);

alter table if exists trails
    add constraint FK_TRAILS_USERS_USERS
    foreign key (users) references users;

create table users_roles (
    user_id int8 not null,
    role_id int8 not null
);

alter table if exists users_roles
    add constraint FK_USERSROLES_ROLES_ID
    FOREIGN KEY (ROLE_ID) REFERENCES ROLES ON DELETE CASCADE ON UPDATE CASCADE;

alter table if exists users_roles
    add constraint FK_USERSROLES_USERS_ID
    FOREIGN KEY (USER_ID) REFERENCES USERS ON DELETE CASCADE ON UPDATE CASCADE;