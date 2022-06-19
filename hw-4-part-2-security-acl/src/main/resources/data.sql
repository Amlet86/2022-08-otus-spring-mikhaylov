--
--Entities
--
insert into authors (name) values ('firstAuthor');
insert into authors (name) values ('secondAuthor');

insert into genres (name) values ('firstGenre');
insert into genres (name) values ('secondGenre');

insert into books (name, author_id, genre_id) values ('firstBook', 1, 1);
insert into books (name, author_id, genre_id) values ('secondBook', 2, 2);

insert into comments (content, book_id) values ('firstComment', 1);
insert into comments (content, book_id) values ('secondComment', 1);
--
--SpringSecurity
--
INSERT INTO authorities (name)
VALUES
('ROLE_ADMIN'),
('ROLE_USER_1'),
('ROLE_USER_2');

INSERT INTO users (name, pwd, authority_id)
VALUES
('admin', '$2a$12$ugmjrIwWFzxh8xLaNO8JX.10RfuybICjweoDiFANFVkOX1lE38D/m', 1),
('user1', '$2a$12$meWd3XNMYxVwyI.dXAZ4Re8sfe32xsv0ktEPKwkjJEEejsRttze5S', 2),
('user2', '$2a$12$Jrt3iM9t7PpAoOHNcFQkcuRtLk.pZlio1QBpHeibr7aF4zkM86B5K', 3);
--
--SpringSecurityAcl
--
INSERT INTO acl_sid (id, principal, sid)
VALUES
(1, 0, 'ROLE_ADMIN'),
(2, 0, 'ROLE_USER_1'),
(3, 0, 'ROLE_USER_2');

INSERT INTO acl_class (id, class)
VALUES
(1, 'ru.amlet.entity.Author'),
(2, 'ru.amlet.entity.Genre'),
(3, 'ru.amlet.entity.Book'),
(4, 'ru.amlet.entity.Comment');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
VALUES
(1, 1, 1, NULL, 1, 0),
(2, 1, 2, NULL, 1, 0),
(3, 3, 1, NULL, 1, 0),
(4, 3, 2, NULL, 1, 0);

--org.springframework.security.acls.domain.BasePermission
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
VALUES
 (1, 1, 1, 1, 16, 1, 1, 1)
,(2, 2, 1, 1, 16, 1, 1, 1)
,(3, 1, 2, 2, 1, 1, 1, 1)
,(4, 2, 2, 3, 1, 1, 1, 1)
,(5, 3, 1, 1, 16, 1, 1, 1)
,(6, 4, 1, 1, 16, 1, 1, 1)
,(7, 3, 2, 2, 1, 1, 1, 1)
,(8, 4, 2, 3, 1, 1, 1, 1)
;