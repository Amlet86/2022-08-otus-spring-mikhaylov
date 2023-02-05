package ru.amlet.caсhe;

import org.springframework.stereotype.Component;
import ru.amlet.entity.mongo.BookMongo;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class BookMongoCache extends ConcurrentHashMap<Long, BookMongo> {
}
