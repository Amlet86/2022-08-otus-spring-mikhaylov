package amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.service.BundleServiceImpl;
import ru.amlet.service.GreetingServiceImpl;
import ru.amlet.service.IOService;
import ru.amlet.service.LeadingScoreServiceImpl;
import ru.amlet.utility.CorrectAnswer;

import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@DisplayName("Имплементация класса LeadingScoreService")
public class LeadingScoreServiceImplTest {

    private final LeadingScoreServiceImpl leadingScoreService = new LeadingScoreServiceImpl();
    private Answer answer;
    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
    }

    @Test
    @DisplayName("метод countScore возвращает 0 если ответ не верный")
    void countScoreShouldReturnZeroIfAnswerIsIncorrect() {
        answer = new Answer("false");
        question.setAnswers(Collections.singletonList(new Answer("right", false)));
        assertEquals(0, leadingScoreService.countScore(question, answer));
    }

    @Test
    @DisplayName("метод countScore возвращает 1 если ответ верный")
    void countScoreShouldReturnOneIfAnswerIsCorrect() {
        answer = new Answer("right");
        question.setAnswers(Collections.singletonList(new Answer("right", true)));
        assertEquals(1, leadingScoreService.countScore(question, answer));
    }

    @Test
    @DisplayName("метод countScore возвращает 1 если ответ из ru Enum верных ответов")
    void countScoreShouldReturnOneIfAnswerFromRuEnum() {
        answer = new Answer(CorrectAnswer.RU.getAnswer());
        assertEquals(1, leadingScoreService.countScore(question, answer));
    }


    @Test
    @DisplayName("метод countScore возвращает 1 если ответ из en Enum верных ответов")
    void countScoreShouldReturnOneIfAnswerFromEnEnum() {
        answer = new Answer(CorrectAnswer.EN.getAnswer());
        assertEquals(1, leadingScoreService.countScore(question, answer));
    }

    @Test
    @DisplayName("метод countScore не учитывает регистр и возвращает 1 если ответ из ru Enum верных ответов")
    void countScoreShouldReturnOneIfAnswerFromRuEnumIgnoreCase() {
        answer = new Answer("путиН");
        assertEquals(1, leadingScoreService.countScore(question, answer));
    }

    @Test
    @DisplayName("метод countScore не учитывает регистр и возвращает 1 если ответ из en Enum верных ответов")
    void countScoreShouldReturnOneIfAnswerFromEnEnumIgnoreCase() {
        answer = new Answer("putiN");
        assertEquals(1, leadingScoreService.countScore(question, answer));
    }

}
