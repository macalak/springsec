-- Security related tables
-- create table
--  application_user (
--    id bigint not null,
--    email varchar(255),
--    enabled boolean not null,
--    first_name varchar(255),
--    last_name varchar(255),
--    password varchar(255),
--    token_expired boolean not null,
--    primary key (id))

-- create table
--  privilege (
--    id bigint not null,
--    name varchar(255),
--    primary key (id))

-- create table
--  role (
--    id bigint not null,
--    name varchar(255),
--    primary key (id))

-- create table
--  roles_privileges (
--    role_id bigint not null,
--    privilege_id bigint not null)

-- create table
--   users_roles (
--     user_id bigint not null,
--     role_id bigint not null)

insert into application_user(ID, EMAIL, ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, TOKEN_EXPIRED) values(1, 'arya@library.do', TRUE, 'Arya', 'Stark', '{noop}stark', FALSE)
insert into application_user(ID, EMAIL, ENABLED, FIRST_NAME, LAST_NAME, PASSWORD, TOKEN_EXPIRED) values(2, 'john@library.do', TRUE, 'John', 'Snow', '{noop}snow', FALSE)

insert into role(ID, NAME) values (1, 'ROLE_USER')
insert into role(ID, NAME) values (2, 'ROLE_ADMIN')

insert into privilege(ID, NAME) values (1, 'BOOK_READ')
insert into privilege(ID, NAME) values (2, 'BOOK_WRITE')

insert into roles_privileges(ROLE_ID, PRIVILEGE_ID) values(1,1)
-- insert into roles_privileges(ROLE_ID, PRIVILEGE_ID) values(2,1)
insert into roles_privileges(ROLE_ID, PRIVILEGE_ID) values(2,2)

insert into users_roles(USER_ID, ROLE_ID) values(1, 1)
insert into users_roles(USER_ID, ROLE_ID) values(2, 2)

-- create table
--  book (id bigint not null,
--        availability varchar(255),
--        cid varchar(255),
--        title varchar(255),
--        publisher varchar(255),
--        author varchar(255),
--        genre varchar(255),
--        isbn varchar(255),
--        primary key (id))


insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (1,'LM-000001','A Game of Thrones','HarperCollins Publishers','George R. R. Martin','9780006479888','Fantasy','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (2,'LM-000002','The Hobbit','Random House Inc','J.R.R. Tolkien','9780345538376','Fantasy','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (3,'LM-000003','The Girl with the Dragon Tattoo','Quercus Publishing Plc','Stieg Larsson','9781849162883','Crime','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (4,'LM-000004','I, Robot','Random House Inc','Isaac Asimov','9780553382563','Scifi','Available');
