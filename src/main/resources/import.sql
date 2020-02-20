insert into user (id, username, password, name, email, ) values (1, 'admin', '123456', '张三', '111@111.com');
insert into user (id, username, password, name, email, ) values (1, 'admin', '123456', '张三', '111@111.com');

insert INTO authority (id, name) values (1, 'ROLE_ADMIN');
insert INTO authority (id, name) values (2, 'ROLE_USER');

insert into user_authority (user_id, authority_id) value (1, 1);
insert into user_authority (user_id, authority_id) value (2, 2);