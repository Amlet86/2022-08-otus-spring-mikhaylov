package ru.amlet.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import ru.amlet.caсhe.AuthorMongoCache;
import ru.amlet.caсhe.BookMongoCache;
import ru.amlet.caсhe.CommentMongoCache;
import ru.amlet.caсhe.GenreMongoCache;
import ru.amlet.entity.jpa.AuthorJpa;
import ru.amlet.entity.jpa.BookJpa;
import ru.amlet.entity.jpa.CommentJpa;
import ru.amlet.entity.jpa.GenreJpa;
import ru.amlet.entity.mongo.AuthorMongo;
import ru.amlet.entity.mongo.BookMongo;
import ru.amlet.entity.mongo.CommentMongo;
import ru.amlet.entity.mongo.GenreMongo;
import ru.amlet.service.ConvertService;

import javax.batch.api.listener.JobListener;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JobConfig {

    private static final int CHUNK_SIZE = 5;
    public static final String IMPORT_LIBRARY_JOB_NAME = "importLibraryJob";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final ConvertService convertService;
    private final MongoTemplate mongoTemplate;
    private final AuthorMongoCache authorMongoCache;
    private final GenreMongoCache genreMongoCache;
    private final BookMongoCache bookMongoCache;
    private final CommentMongoCache commentMongoCache;

    @Bean
    public Job jpaToMongoJob(Step authorMongoToJpaStep, Step genreMongoToJpaStep,
                             Step bookMongoToJpaStep, Step commentMongoToJpaStep
    ) {
        return jobBuilderFactory
                .get(IMPORT_LIBRARY_JOB_NAME)
                .flow(authorMongoToJpaStep)
                .next(genreMongoToJpaStep)
                .next(bookMongoToJpaStep)
                .next(commentMongoToJpaStep)
                .end()
                .build();
    }

    // region Author
    @Bean
    public Step authorMongoToJpaStep(ItemReader<AuthorJpa> authorReader,
                                     ItemProcessor<AuthorJpa, AuthorMongo> authorProcessor,
                                     ItemWriter<AuthorMongo> authorWriter) {
        return stepBuilderFactory
                .get("authorStep")
                .<AuthorJpa, AuthorMongo>chunk(CHUNK_SIZE)
                .reader(authorReader)
                .processor(authorProcessor)
                .writer(authorWriter)
                .listener(new JobListener() {
                    @Override
                    public void beforeJob() {
                        List<AuthorMongo> authorMongoList = mongoTemplate.findAll(AuthorMongo.class);
                        authorMongoList.forEach(authorMongo -> {
                            authorMongoCache.put(authorMongo.getSourceId(), authorMongo);
                        });
                    }

                    @Override
                    public void afterJob() {

                    }
                })
                .listener(new ItemWriteListener<>() {
                    @Override
                    public void beforeWrite(@NonNull List<? extends AuthorMongo> list) {

                    }

                    @Override
                    public void afterWrite(@NonNull List<? extends AuthorMongo> authorMongoList) {
                        authorMongoList.forEach(authorMongo -> {
                            authorMongoCache.put(authorMongo.getSourceId(), authorMongo);
                        });
                    }

                    @Override
                    public void onWriteError(@NonNull Exception e, @NonNull List<? extends AuthorMongo> list) {

                    }
                })
                .build();
    }

    @Bean
    public JpaPagingItemReader<AuthorJpa> authorReader() {
        return new JpaPagingItemReaderBuilder<AuthorJpa>()
                .name("authorJpaReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("from AuthorJpa")
                .build();
    }

    @Bean
    public ItemProcessor<AuthorJpa, AuthorMongo> authorProcessor() {
        return authorJpa -> convertService.authorJpaToMongo(authorJpa, authorMongoCache);
    }

    @Bean
    public MongoItemWriter<AuthorMongo> authorWriter() {
        return new MongoItemWriterBuilder<AuthorMongo>()
                .template(mongoTemplate)
                .build();
    }
    // endregion

    // region Genre
    @Bean
    public Step genreMongoToJpaStep(ItemReader<GenreJpa> genreReader,
                                    ItemProcessor<GenreJpa, GenreMongo> genreProcessor,
                                    ItemWriter<GenreMongo> genreWriter) {
        return stepBuilderFactory
                .get("genreStep")
                .<GenreJpa, GenreMongo>chunk(CHUNK_SIZE)
                .reader(genreReader)
                .processor(genreProcessor)
                .writer(genreWriter)
                .listener(new JobListener() {
                    @Override
                    public void beforeJob() {
                        List<GenreMongo> genreMongoList = mongoTemplate.findAll(GenreMongo.class);
                        genreMongoList.forEach(genreMongo -> {
                            genreMongoCache.put(genreMongo.getSourceId(), genreMongo);
                        });
                    }

                    @Override
                    public void afterJob() {

                    }
                })
                .listener(new ItemWriteListener<>() {

                    @Override
                    public void beforeWrite(@NonNull List<? extends GenreMongo> list) {

                    }

                    @Override
                    public void afterWrite(@NonNull List<? extends GenreMongo> genreMongoList) {
                        genreMongoList.forEach(genreMongo -> {
                            genreMongoCache.put(genreMongo.getSourceId(), genreMongo);
                        });
                    }

                    @Override
                    public void onWriteError(@NonNull Exception e, @NonNull List<? extends GenreMongo> list) {

                    }
                })
                .build();
    }

    @Bean
    public JpaPagingItemReader<GenreJpa> genreReader() {
        return new JpaPagingItemReaderBuilder<GenreJpa>()
                .name("genreJpaReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("from GenreJpa")
                .build();
    }

    @Bean
    public ItemProcessor<GenreJpa, GenreMongo> genreProcessor() {
        return genreJpa -> convertService.genreJpaToMongo(genreJpa, genreMongoCache);
    }

    @Bean
    public MongoItemWriter<GenreMongo> genreWriter() {
        return new MongoItemWriterBuilder<GenreMongo>()
                .template(mongoTemplate)
                .build();
    }
    // endregion

    // region Book
    @Bean
    public Step bookMongoToJpaStep(ItemReader<BookJpa> bookReader,
                                   ItemProcessor<BookJpa, BookMongo> bookProcessor,
                                   ItemWriter<BookMongo> bookWriter) {
        return stepBuilderFactory
                .get("bookStep")
                .<BookJpa, BookMongo>chunk(CHUNK_SIZE)
                .reader(bookReader)
                .processor(bookProcessor)
                .writer(bookWriter)
                .listener(new JobListener() {
                    @Override
                    public void beforeJob() {
                        List<BookMongo> bookMongoList = mongoTemplate.findAll(BookMongo.class);
                        bookMongoList.forEach(bookMongo -> {
                            bookMongoCache.put(bookMongo.getSourceId(), bookMongo);
                        });
                    }

                    @Override
                    public void afterJob() {

                    }
                })
                .listener(new ItemWriteListener<>() {

                    @Override
                    public void beforeWrite(@NonNull List<? extends BookMongo> list) {

                    }

                    @Override
                    public void afterWrite(@NonNull List<? extends BookMongo> bookMongoList) {
                        bookMongoList.forEach(bookMongo -> {
                            bookMongoCache.put(bookMongo.getSourceId(), bookMongo);
                        });
                    }

                    @Override
                    public void onWriteError(@NonNull Exception e, @NonNull List<? extends BookMongo> list) {

                    }
                })
                .build();
    }

    @Bean
    public JpaPagingItemReader<BookJpa> bookReader() {
        return new JpaPagingItemReaderBuilder<BookJpa>()
                .name("bookJpaReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("from BookJpa")
                .build();
    }

    @Bean
    public ItemProcessor<BookJpa, BookMongo> bookProcessor() {
        return bookJpa -> convertService.bookJpaToMongo(bookJpa, bookMongoCache, authorMongoCache, genreMongoCache);
    }

    @Bean
    public MongoItemWriter<BookMongo> bookWriter() {
        return new MongoItemWriterBuilder<BookMongo>()
                .template(mongoTemplate)
                .build();
    }
    // endregion

    // region Comment
    @Bean
    public Step commentMongoToJpaStep(ItemReader<CommentJpa> commentReader,
                                      ItemProcessor<CommentJpa, CommentMongo> commentProcessor,
                                      ItemWriter<CommentMongo> commentWriter) {
        return stepBuilderFactory
                .get("commentStep")
                .<CommentJpa, CommentMongo>chunk(CHUNK_SIZE)
                .reader(commentReader)
                .processor(commentProcessor)
                .writer(commentWriter)
                .listener(new JobListener() {
                    @Override
                    public void beforeJob() {
                        List<CommentMongo> commentMongoList = mongoTemplate.findAll(CommentMongo.class);
                        commentMongoList.forEach(commentMongo -> {
                            commentMongoCache.put(commentMongo.getSourceId(), commentMongo);
                        });
                    }

                    @Override
                    public void afterJob() {

                    }
                })
                .listener(new ItemWriteListener<>() {
                    @Override
                    public void beforeWrite(@NonNull List<? extends CommentMongo> list) {

                    }

                    @Override
                    public void afterWrite(@NonNull List<? extends CommentMongo> commentMongoList) {
                        commentMongoList.forEach(commentMongo -> {
                            commentMongoCache.put(commentMongo.getSourceId(), commentMongo);
                        });
                    }

                    @Override
                    public void onWriteError(@NonNull Exception e, @NonNull List<? extends CommentMongo> list) {

                    }
                })
                .build();
    }

    @Bean
    public JpaPagingItemReader<CommentJpa> commentReader() {
        return new JpaPagingItemReaderBuilder<CommentJpa>()
                .name("commentJpaReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("from CommentJpa")
                .build();
    }

    @Bean
    public ItemProcessor<CommentJpa, CommentMongo> commentProcessor() {
        return commentJpa -> convertService.commentJpaToMongo(commentJpa, commentMongoCache,
                bookMongoCache, authorMongoCache, genreMongoCache);
    }

    @Bean
    public MongoItemWriter<CommentMongo> commentWriter() {
        return new MongoItemWriterBuilder<CommentMongo>()
                .template(mongoTemplate)
                .build();
    }
    // endregion
}
