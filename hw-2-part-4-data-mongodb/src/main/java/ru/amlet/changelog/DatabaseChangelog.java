package ru.amlet.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.amlet.entity.Author;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;
import ru.amlet.entity.Genre;
import ru.amlet.repositories.AuthorRepository;
import ru.amlet.repositories.BookRepository;
import ru.amlet.repositories.CommentRepository;
import ru.amlet.repositories.GenreRepository;

@ChangeLog
public class DatabaseChangelog {

    private final Author firstAuthor = new Author("firstAuthor");
    private final Author secondAuthor = new Author("secondAuthor");
    private final Genre firstGenre = new Genre("firstGenre");
    private final Genre secondGenre = new Genre("secondGenre");
    private final Book firstBook = Book.builder().name("firstBook").author(firstAuthor).genre(firstGenre).build();
    private final Book secondBook = Book.builder().name("secondBook").author(firstAuthor).genre(firstGenre).build();
    private final Book thirdBook = Book.builder().name("thirdBook").author(firstAuthor).genre(firstGenre).build();
    private final Comment firstComment = new Comment(null, "firstComment", firstBook);
    private final Comment secondComment = new Comment(null, "secondComment", firstBook);
    private final Comment thirdComment = new Comment(null, "thirdComment", thirdBook);

    @ChangeSet(order = "001", id = "dropDb", author = "amlet", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "amlet")
    public void insertAuthors(AuthorRepository repository) {
        repository.save(firstAuthor);
        repository.save(secondAuthor);
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "amlet")
    public void insertGenres(GenreRepository repository) {
        repository.save(firstGenre);
        repository.save(secondGenre);
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "amlet")
    public void insertBooks(BookRepository repository) {
        repository.save(firstBook);
        repository.save(secondBook);
        repository.save(thirdBook);
    }

    @ChangeSet(order = "005", id = "insertComments", author = "amlet")
    public void insertBooks(CommentRepository repository) {
        repository.save(firstComment);
        repository.save(secondComment);
        repository.save(thirdComment);
    }

}
