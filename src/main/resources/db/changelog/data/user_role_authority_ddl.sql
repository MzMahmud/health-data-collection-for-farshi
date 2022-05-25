CREATE TABLE app_user
(
    id         BIGINT NOT NULL,
    username   VARCHAR(255),
    password   VARCHAR(255),
    is_enabled BOOLEAN,
    CONSTRAINT pk_app_user PRIMARY KEY (id)
);


create table app_user_roles
(
    app_user_id int8 not null,
    roles_id    int8 not null,
    primary key (app_user_id, roles_id)
);


create table authority
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);


create table role
(
    id   int8 not null,
    name varchar(255),
    primary key (id)
);


create table role_authorities
(
    role_id        int8 not null,
    authorities_id int8 not null,
    primary key (role_id, authorities_id)
);

create sequence seq_app_user start 1 increment 50;
create sequence seq_authority start 1 increment 50;
create sequence seq_role start 1 increment 50;


alter table app_user_roles
    add constraint fk_roles_id
        foreign key (roles_id)
            references role;


alter table app_user_roles
    add constraint fk_app_user_id
        foreign key (app_user_id)
            references app_user;


alter table role_authorities
    add constraint fk_authorities_id
        foreign key (authorities_id)
            references authority;


alter table role_authorities
    add constraint fk_role_id
        foreign key (role_id)
            references role;