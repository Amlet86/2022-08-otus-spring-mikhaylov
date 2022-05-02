insert into authors (name) values ('firstAuthor');
insert into authors (name) values ('secondAuthor');
insert into authors (name) values ('thirdAuthor');

insert into genres (name) values ('firstGenre');
insert into genres (name) values ('secondGenre');
insert into genres (name) values ('thirdGenre');

insert into books (name, author_id, genre_id) values ('firstBook', 1, 1);
insert into books (name, author_id, genre_id) values ('secondBook', 2, 2);

insert into comments (content, book_id) values ('firstComment', 1);
insert into comments (content, book_id) values ('secondComment', 1);
