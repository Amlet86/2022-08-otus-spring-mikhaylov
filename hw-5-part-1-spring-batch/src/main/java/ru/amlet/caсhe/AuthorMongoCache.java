package ru.amlet.caсhe;

import org.springframework.stereotype.Component;
import ru.amlet.entity.mongo.AuthorMongo;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthorMongoCache extends ConcurrentHashMap<Long, AuthorMongo> {
}
