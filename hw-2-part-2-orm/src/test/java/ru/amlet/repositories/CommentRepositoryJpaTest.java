package ru.amlet.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.amlet.entity.Book;
import ru.amlet.entity.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(CommentRepositoryJpa.class)
@DisplayName("Имплементация класса BookRepository")
class CommentRepositoryJpaTest {

    @Autowired
    private CommentRepositoryJpa commentRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Добавляет комментарий в БД")
    void shouldCreateComment() {
        var book = testEntityManager.find(Book.class, 1L);
        var expectedComment = new Comment(0, "content", book);
        var actualComment = commentRepository.saveComment(expectedComment);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Возвращает ожидаемый комментарий по его content")
    void shouldReturnExpectedCommentByContent() {
        var expectedComment = testEntityManager.find(Comment.class, 1L);
        var actualComment = commentRepository.getByContent(expectedComment.getContent());
        assertThat(actualComment).contains(expectedComment);
    }

    @Test
    @DisplayName("Возвращает ожидаемый комментарий по его id")
    void shouldReturnExpectedCommentById() {
        var expectedComment = testEntityManager.find(Comment.class, 1L);
        var actualComment = commentRepository.getById(expectedComment.getId()).orElse(null);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Возвращает список комментариев из БД")
    void shouldReturnExpectedCommentsList() {
        var expectedComment = testEntityManager.find(Comment.class, 1L);
        var actualCommentsList = commentRepository.getAll();
        assertEquals(2, actualCommentsList.size());
        assertThat(actualCommentsList).contains(expectedComment);
    }

    @Test
    @DisplayName("Изменяет комментарий в БД")
    void shouldUpdateComment() {
        var expectedComment = testEntityManager.find(Comment.class, 1L);
        expectedComment.setContent("UpdatedContent");
        var actualComment = commentRepository.saveComment(expectedComment);
        assertEquals(expectedComment, actualComment);
    }

    @Test
    @DisplayName("Удаляет комментарий по его id")
    void shouldDeleteCommentById() {
        var actualComment = testEntityManager.find(Comment.class, 1L);
        assertNotNull(actualComment);
        testEntityManager.detach(actualComment);
        commentRepository.deleteComment(1);
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