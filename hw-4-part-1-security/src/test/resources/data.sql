insert into roles (name) values ('ADMIN'), ('USER');

insert into users (name, pwd, role_id)
values ('admin', '{bcrypt}$2a$12$ugmjrIwWFzxh8xLaNO8JX.10RfuybICjweoDiFANFVkOX1lE38D/m', 1), ('user', '{noop}password', 2);

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
