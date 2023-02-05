package ru.amlet.entity.mongo;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class BookMongo {

    @Id
    private ObjectId id;

    private String name;

    @DBRef
    private AuthorMongo authorMongo;

    @DBRef
    private GenreMongo genreMongo;

    private Long sourceId;

}
