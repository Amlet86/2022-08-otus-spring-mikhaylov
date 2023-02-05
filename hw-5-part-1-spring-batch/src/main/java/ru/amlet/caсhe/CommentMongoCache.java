package ru.amlet.caсhe;

import org.springframework.stereotype.Component;
import ru.amlet.entity.mongo.CommentMongo;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommentMongoCache extends ConcurrentHashMap<Long, CommentMongo> {
}
