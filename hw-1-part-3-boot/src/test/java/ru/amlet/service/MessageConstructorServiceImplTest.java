package ru.amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.QuizState;
import ru.amlet.service.LocalizationService;
import ru.amlet.service.MessageConstructorService;
import ru.amlet.service.MessageConstructorServiceImpl;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Имплементация класса MessageConstructorService")
public class MessageConstructorServiceImplTest {

    @Mock
    private LocalizationService localizationService;
    private final String bundleWin = "result.win";
    private final String bundleLose = "result.lose";
    private MessageConstructorService messageConstructorService;

    @BeforeEach
    void setUp() {
        messageConstructorService = new MessageConstructorServiceImpl(localizationService, bundleWin, bundleLose);
    }

    @Test
    @DisplayName("метод createAnswerMessage возвращает конкатенированные ответы")
    void createAnswerMessageShouldReturnConcatenatedAnswers() {
        Answer answer1 = new Answer("first");
        Answer answer2 = new Answer("second");
        Answer answer3 = new Answer("third");
        Question question = new Question();
        question.setAnswers(Arrays.asList(answer1, answer2, answer3));
        String expectedAnswer = "1.first 2.second 3.third ";
        assertEquals(expectedAnswer, messageConstructorService.createAnswerMessage(question));
    }

    @Test
    @DisplayName("метод createResultMessage возвращает определенное en сообщение позитивного результата")
    void createResultMessageShouldReturnEnExpectedWinMessage() {
        given(localizationService.getLocalizedMessage(bundleWin))
                .willReturn("Dear %s your result %s it's a good result. Congratulation!");
        Player player = new Player("Amlet");
        int lowestPassingScore = 3;
        QuizState quizState = new QuizState(player, lowestPassingScore);
        quizState.setScore(4);
        String expectedMessage = "Dear Amlet your result 4 it's a good result. Congratulation!";
        assertEquals(expectedMessage, messageConstructorService.createResultMessage(quizState));
    }

    @Test
    @DisplayName("метод createResultMessage возвращает определенное en сообщение негативного результата")
    void createResultMessageShouldReturnEnExpectedLoseMessage() {
        given(localizationService.getLocalizedMessage(bundleLose))
                .willReturn("Dear %s your result %s it's a terrible result. Try again.");
        Player player = new Player("Amlet");
        int lowestPassingScore = 3;
        QuizState quizState = new QuizState(player, lowestPassingScore);
        quizState.setScore(2);
        String expectedMessage = "Dear Amlet your result 2 it's a terrible result. Try again.";
        assertEquals(expectedMessage, messageConstructorService.createResultMessage(quizState));
    }

    @Test
    @DisplayName("метод createResultMessage возвращает определенное ru сообщение позитивного результата")
    void createResultMessageShouldReturnRuExpectedWinMessage() {
        given(localizationService.getLocalizedMessage(bundleWin))
                .willReturn("Дорогой %s твой счёт %s это хороший результат. Поздравляю!");
        Player player = new Player("Андрей");
        int lowestPassingScore = 3;
        QuizState quizState = new QuizState(player, lowestPassingScore);
        quizState.setScore(4);
        String expectedMessage = "Дорогой Андрей твой счёт 4 это хороший результат. Поздравляю!";
        assertEquals(expectedMessage, messageConstructorService.createResultMessage(quizState));
    }

    @Test
    @DisplayName("метод createResultMessage возвращает определенное ru сообщение негативного результата")
    void createResultMessageShouldReturnRuExpectedLoseMessage() {
        given(localizationService.getLocalizedMessage(bundleLose))
                .willReturn("Дорогой %s твой счёт %s это ужасный результат. Попробуй снова.");
        Player player = new Player("Андрей");
        int lowestPassingScore = 3;
        QuizState quizState = new QuizState(player, lowestPassingScore);
        quizState.setScore(2);
        String expectedMessage = "Дорогой Андрей твой счёт 2 это ужасный результат. Попробуй снова.";
        assertEquals(expectedMessage, messageConstructorService.createResultMessage(quizState));
    }

}
