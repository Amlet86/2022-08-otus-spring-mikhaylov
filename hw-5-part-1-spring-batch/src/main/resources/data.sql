insert into authors (id, name) values (1, 'firstAuthor');
insert into authors (id, name) values (2, 'secondAuthor');

insert into genres (id, name) values (1, 'firstGenre');
insert into genres (id, name) values (2, 'secondGenre');

insert into books values (1, 'firstBook', 1, 1);
insert into books values (2, 'secondBook', 1, 2);
insert into books values (3, 'thirdBook', 2, 2);

insert into comments (id, content, book_jpa_id) values (1, 'firstComment', 1);
insert into comments (id, content, book_jpa_id) values (2, 'secondComment', 2);
insert into comments (id, content, book_jpa_id) values (3, 'thirdComment', 3);
