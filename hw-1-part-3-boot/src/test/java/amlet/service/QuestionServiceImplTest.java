package amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.amlet.dao.QuestionDao;
import ru.amlet.entity.Question;
import ru.amlet.service.QuestionServiceImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    @DisplayName("метод getQuestions возвращает список содержащий объекты класса Question")
    void shouldContainsOnlyQuestionClasses() {
        given(questionDao.findQuestions())
                .willReturn(Collections.singletonList(new Question()));
        questionService.getQuestions()
                .forEach(question -> assertEquals(Question.class, question.getClass()));
    }

}
