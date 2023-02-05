package ru.amlet.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.amlet.entity.jpa.AuthorJpa;
import ru.amlet.entity.jpa.BookJpa;
import ru.amlet.entity.jpa.CommentJpa;
import ru.amlet.entity.jpa.GenreJpa;
import ru.amlet.entity.mongo.AuthorMongo;
import ru.amlet.entity.mongo.BookMongo;
import ru.amlet.entity.mongo.CommentMongo;
import ru.amlet.entity.mongo.GenreMongo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConvertService {

    public AuthorMongo authorJpaToMongo(AuthorJpa authorJpa, Map<Long, AuthorMongo> authorMongoMap) {
        return authorMongoMap.getOrDefault(authorJpa.getId(),
                new AuthorMongo(new ObjectId(), authorJpa.getName(), authorJpa.getId()));
    }

    public GenreMongo genreJpaToMongo(GenreJpa genreJpa, Map<Long, GenreMongo> genreMongoMap) {
        return genreMongoMap.getOrDefault(genreJpa.getId(),
                new GenreMongo(new ObjectId(), genreJpa.getName(), genreJpa.getId()));
    }

    public BookMongo bookJpaToMongo(BookJpa bookJpa, Map<Long, BookMongo> bookMongoMap,
                                    Map<Long, AuthorMongo> authorMongoMap, Map<Long, GenreMongo> genreMongoMap) {
        return bookMongoMap.getOrDefault(bookJpa.getId(),
                BookMongo.builder()
                        .id(new ObjectId())
                        .name(bookJpa.getName())
                        .authorMongo(authorJpaToMongo(bookJpa.getAuthor(), authorMongoMap))
                        .genreMongo(genreJpaToMongo(bookJpa.getGenre(), genreMongoMap))
                        .sourceId(bookJpa.getId())
                        .build());
    }

    public CommentMongo commentJpaToMongo(CommentJpa commentJpa, Map<Long, CommentMongo> commentMongoMap,
                                          Map<Long, BookMongo> bookMongoMap, Map<Long, AuthorMongo> authorMongoMap,
                                          Map<Long, GenreMongo> genreMongoMap) {
        return commentMongoMap.getOrDefault(commentJpa.getId(),
                new CommentMongo(
                        new ObjectId(),
                        commentJpa.getContent(),
                        bookJpaToMongo(commentJpa.getBookJpa(), bookMongoMap, authorMongoMap, genreMongoMap),
                        commentJpa.getId()));
    }

}
