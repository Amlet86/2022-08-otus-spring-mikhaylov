package ru.amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.amlet.dao.QuestionDao;
import ru.amlet.dto.Question;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
                .willReturn(Collections.singletonList(new Question()));

        assertFalse(questionService.getQuestions().isEmpty());
    }

}
