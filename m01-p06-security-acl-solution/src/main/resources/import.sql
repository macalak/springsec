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

-- ACL data
-- mask values: READ=1, WRITE=2 (check ACL BasePermission class)
INSERT INTO acl_sid (id, principal, sid) VALUES (1, 0, 'ROLE_READER'),(2, 0, 'ROLE_WRITER');
INSERT INTO acl_class (id, class) VALUES (1, 'ite.librarymaster.domain.model.Book');
INSERT INTO system_message(id,content) VALUES (1,'First Level Message'), (2,'Second Level Message'), (3,'Third Level Message');
INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES (1, 1, 1, NULL, 1, 0), (2, 1, 2, NULL, 2, 0);
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES (1, 1, 1, 1, 1, 1, 1, 1),(2, 2, 1, 1, 1, 1, 1, 1),(3, 1, 2, 1, 2, 1, 1, 1);

insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (1,'LM-000001','A Game of Thrones','HarperCollins Publishers','George R. R. Martin','9780006479888','Fantasy','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (2,'LM-000002','The Hobbit','Random House Inc','J.R.R. Tolkien','9780345538376','Fantasy','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (3,'LM-000003','The Girl with the Dragon Tattoo','Quercus Publishing Plc','Stieg Larsson','9781849162883','Crime','Available');
insert into BOOK (ID, CID, TITLE, PUBLISHER, AUTHOR, ISBN, GENRE, AVAILABILITY) values (4,'LM-000004','I, Robot','Random House Inc','Isaac Asimov','9780553382563','Scifi','Available');


