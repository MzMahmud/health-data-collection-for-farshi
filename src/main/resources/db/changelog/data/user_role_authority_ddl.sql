create table app_user
(
    id         bigint not null,
    username   varchar(255),
    password   varchar(255),
    is_enabled boolean,
    constraint pk_app_user primary key (id)
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

insert into role (id, name)
values (1, 'ADMIN'),
       (2, 'USER'),
       (3, 'DATA_COLLECTOR');

insert into authority (id, name)
values (1, 'HEALTH_DATA_ADD'),
       (2, 'HEALTH_DATA_EDIT'),
       (3, 'HEALTH_DATA_DELETE'),
       (4, 'HEALTH_DATA_VIEW');

insert into role_authorities (role_id, authorities_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 4),
       (3, 1),
       (3, 4);