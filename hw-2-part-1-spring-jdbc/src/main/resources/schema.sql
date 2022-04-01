CREATE TABLE authors (id IDENTITY PRIMARY KEY, name VARCHAR(255));

CREATE TABLE genres (id IDENTITY PRIMARY KEY, name VARCHAR(255));

CREATE TABLE books (id IDENTITY PRIMARY KEY,
                    name VARCHAR(255),
                    author_id BIGINT,
                    genre_id BIGINT,
                    FOREIGN KEY (author_id) REFERENCES authors(id),
                    FOREIGN KEY (genre_id) REFERENCES genres(id));