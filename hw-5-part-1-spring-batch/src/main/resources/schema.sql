CREATE TABLE authors (id IDENTITY PRIMARY KEY, name VARCHAR(255));

CREATE TABLE genres (id IDENTITY PRIMARY KEY, name VARCHAR(255));

CREATE TABLE books (id IDENTITY PRIMARY KEY,
                    name VARCHAR(255),
                    author_id BIGINT,
                    genre_id BIGINT,
                    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE,
                    FOREIGN KEY (genre_id) REFERENCES genres(id) ON DELETE CASCADE);

CREATE TABLE comments (id IDENTITY PRIMARY KEY,
                        content VARCHAR(255),
                        book_jpa_id BIGINT,
                        FOREIGN KEY (book_jpa_id) REFERENCES books(id) ON DELETE CASCADE);
