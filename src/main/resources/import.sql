insert into user (id, username, password, name, email) values (1, 'admin1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '张三1', '111@111.com');
insert into user (id, username, password, name, email) values (2, 'admin2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '张三2', '222@2222.com');

insert INTO authority (id, name) values (1, 'ROLE_ADMIN');
insert INTO authority (id, name) values (2, 'ROLE_USER');

insert into user_authority (user_id, authority_id) value (1, 1);
insert into user_authority (user_id, authority_id) value (2, 2);