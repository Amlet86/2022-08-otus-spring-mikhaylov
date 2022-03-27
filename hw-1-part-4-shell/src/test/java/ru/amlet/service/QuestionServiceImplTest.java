package ru.amlet.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.amlet.dao.QuestionDao;
import ru.amlet.entity.Question;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Имплементация класса QuestionService")
public class QuestionServiceImplTest {

    @MockBean
    private QuestionDao questionDao;
    @Autowired
    private QuestionService questionService;

    @Test
    @DisplayName("метод getQuestions возвращает не пустой список")
    void shouldReturnList() {
        given(questionDao.findQuestions())
                .willReturn(Collections.singletonList(new Question()));
        assertFalse(questionService.getQuestions().isEmpty());
    }

    @Test
    @DisplayName("метод getQuestions возвращает список содержащий объекты класса Question")
    void shouldContainsOnlyQuestionClasses() {
        given(questionDao.findQuestions())
                .willReturn(Collections.singletonList(new Question()));
        questionService.getQuestions()
                .forEach(question -> assertEquals(Question.class, question.getClass()));
    }

}
