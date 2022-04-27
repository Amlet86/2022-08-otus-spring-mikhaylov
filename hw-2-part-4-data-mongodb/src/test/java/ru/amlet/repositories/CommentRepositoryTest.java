package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@DisplayName("Интерфейс CommentRepository")
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName("Добавляет комментарий в БД")
    void shouldCreateComment() {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("thirdBook"));
        var book = mongoTemplate.findOne(query, Book.class);
        var expectedComment = new Comment(null, "content", book);
        var actualComment = commentRepository.save(expectedComment);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Возвращает ожидаемый комментарий по его content")
    void shouldReturnExpectedCommentByContent() {
        Query query = new Query();
        query.addCriteria(Criteria.where("content").is("thirdComment"));
        var expectedComment = mongoTemplate.findOne(query, Comment.class);
        var actualComment = commentRepository.findByContent(expectedComment.getContent());
        assertThat(actualComment).contains(expectedComment);
    }

    @Test
    @DisplayName("Возвращает ожидаемый комментарий по его id")
    void shouldReturnExpectedCommentById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("content").is("thirdComment"));
        var expectedComment = mongoTemplate.findOne(query, Comment.class);
        var actualComment = commentRepository.findById(expectedComment.getId()).orElse(null);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Изменяет комментарий в БД")
    void shouldUpdateComment() {
        Query query = new Query();
        query.addCriteria(Criteria.where("content").is("firstComment"));
        var expectedComment = mongoTemplate.findOne(query, Comment.class);
        expectedComment.setContent("updatedContent");
        var actualComment = commentRepository.save(expectedComment);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Удаляет комментарий по его id")
    void shouldDeleteCommentById() {
        Query query = new Query();
        query.addCriteria(Criteria.where("content").is("secondComment"));
        var actualComment = mongoTemplate.findOne(query, Comment.class);
        assertNotNull(actualComment);
        commentRepository.deleteById(actualComment.getId());
        var deletedComment = mongoTemplate.findOne(query, Comment.class);
        assertNull(deletedComment);
    }

}