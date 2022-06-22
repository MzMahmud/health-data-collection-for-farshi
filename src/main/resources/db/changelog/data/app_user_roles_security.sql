insert into authority (id, name)
values (5, 'APP_USER_ADD'),
       (6, 'APP_USER_ROLES_VIEW'),
       (7, 'APP_USER_ROLES_UPDATE');

insert into role_authorities (role_id, authorities_id)
values (1, 5),
       (1, 6),
       (1, 7);

alter sequence seq_app_user restart with 4;
select nextval ('seq_app_user');