package ru.amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.amlet.dao.QuestionDao;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Имплементация класса QuestionService")
public class QuestionServiceImplTest {

    @Mock
    private QuestionDao questionDao;

    private QuestionServiceImpl questionService;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionDao);
    }

    @Test
    @DisplayName("метод getQuestions возвращает не пустой список")
    void shouldReturnList() {
        given(questionDao.findQuestions())
                .willReturn(Collections.singletonList(new QuestionDto()));
        assertFalse(questionService.getQuestionsDto().isEmpty());
    }

    @Test
    @DisplayName("метод getQuestions возвращает список содержащий объекты класса QuestionDto")
    void shouldContainsOnlyQuestionDtoClasses() {
        given(questionDao.findQuestions())
                .willReturn(Collections.singletonList(new QuestionDto()));
        questionService.getQuestionsDto()
                .forEach(question -> assertEquals(question.getClass(), QuestionDto.class));
    }

    @Test
    @DisplayName("метод getQuestions возвращает список содержащий объекты класса Question")
    void shouldContainsOnlyQuestionClasses() {
        given(questionDao.findQuestions())
                .willReturn(Collections.singletonList(new QuestionDto()));
        questionService.getQuestions()
                .forEach(question -> assertEquals(question.getClass(), Question.class));
    }

    @Test
    @DisplayName("метод convertQuestionFromQuestionDto преобразует объект класса QuestionDto в Question")
    void shouldConvertQuestionDtoToQuestionClass() {
        QuestionDto questionDto = new QuestionDto();
        assertEquals(Question.class,
                questionService.convertQuestionFromQuestionDto(questionDto).getClass());
    }

}
