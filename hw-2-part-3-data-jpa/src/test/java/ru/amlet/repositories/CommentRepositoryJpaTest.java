package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Интерфейс BookRepository")
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Добавляет комментарий в БД")
    void shouldCreateComment() {
        var book = testEntityManager.find(Book.class, 1L);
        var expectedComment = new Comment(0, "content", book);
        var actualComment = commentRepository.save(expectedComment);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Возвращает ожидаемый комментарий по его content")
    void shouldReturnExpectedCommentByContent() {
        var expectedComment = testEntityManager.find(Comment.class, 1L);
        var actualComment = commentRepository.findByContent(expectedComment.getContent());
        assertThat(actualComment).contains(expectedComment);
    }

    @Test
    @DisplayName("Возвращает ожидаемый комментарий по его id")
    void shouldReturnExpectedCommentById() {
        var expectedComment = testEntityManager.find(Comment.class, 1L);
        var actualComment = commentRepository.findById(expectedComment.getId()).orElse(null);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Изменяет комментарий в БД")
    void shouldUpdateComment() {
        var expectedComment = testEntityManager.find(Comment.class, 1L);
        expectedComment.setContent("UpdatedContent");
        var actualComment = commentRepository.save(expectedComment);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Удаляет комментарий по его id")
    void shouldDeleteCommentById() {
        var actualComment = testEntityManager.find(Comment.class, 1L);
        assertNotNull(actualComment);
        testEntityManager.detach(actualComment);
        commentRepository.deleteAllById(List.of(1L));
        var deletedComment = testEntityManager.find(Comment.class, 1L);
        assertNull(deletedComment);
    }

    @Test
    @DisplayName("Возвращает ожидаемое количество комментариев из БД")
    void shouldReturnExpectedCommentsCount() {
        var actualCommentsCount = commentRepository.count();
        assertEquals(2, actualCommentsCount);
    }
}