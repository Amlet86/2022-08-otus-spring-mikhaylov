package ru.amlet.caсhe;

import org.springframework.stereotype.Component;
import ru.amlet.entity.mongo.GenreMongo;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class GenreMongoCache extends ConcurrentHashMap<Long, GenreMongo> {
}
