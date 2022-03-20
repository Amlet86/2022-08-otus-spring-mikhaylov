package amlet.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.QuizState;
import ru.amlet.service.MessageConstructorService;
import ru.amlet.service.MessageConstructorServiceImpl;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Имплементация класса MessageConstructorService")
public class MessageConstructorServiceImplTest {

    MessageConstructorService messageConstructorService = new MessageConstructorServiceImpl();

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
    @DisplayName("метод createResultMessage возвращает определенное сообщение позитивного результата")
    void createResultMessageShouldReturnExpectedWinMessage() {
        Player player = new Player("Amlet");
        int lowestPassingScore = 3;
        QuizState quizState = new QuizState(player, lowestPassingScore);
        quizState.setScore(4);
        String expectedMessage = "Dear Amlet your result: 4 it's a good result. Congratulation!";
        assertEquals(expectedMessage, messageConstructorService.createResultMessage(quizState));
    }

    @Test
    @DisplayName("метод createResultMessage возвращает определенное сообщение негативного результата")
    void createResultMessageShouldReturnExpectedLoseMessage() {
        Player player = new Player("Amlet");
        int lowestPassingScore = 3;
        QuizState quizState = new QuizState(player, lowestPassingScore);
        quizState.setScore(2);
        String expectedMessage = "Dear Amlet your result: 2 it's a terrible result. Try again.";
        assertEquals(expectedMessage, messageConstructorService.createResultMessage(quizState));
    }

}
