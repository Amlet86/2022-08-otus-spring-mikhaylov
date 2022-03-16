package amlet.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import ru.amlet.dao.QuestionDaoCsv;
import ru.amlet.entity.Question;
import ru.amlet.exception.CsvReadException;
import ru.amlet.utility.QuestionConverter;
import ru.amlet.utility.QuizLocalization;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Имплементация класса QuestionDao")
public class QuestionDaoCsvTest {

    @Autowired
    private QuizLocalization quizLocalization;
    private final QuestionConverter questionConverter = new QuestionConverter(quizLocalization);
    private final String csvName = "qa.csv";

    @Test
    @DisplayName("метод findQuestions, прочитав csv, возвращает не пустой список")
    void getQuestionsShouldReturnIsNotEmptyList() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName, questionConverter);
        assertFalse(questionDao.findQuestions().isEmpty());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав csv, возвращает список из 6 объектов")
    void csvFileShouldContainsSixQuestions() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName, questionConverter);
        assertEquals(5, questionDao.findQuestions().size());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав csv, возвращает список из объектов класса Question")
    void csvFileShouldContainsOnlyQuestionClasses() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName, questionConverter);
        questionDao.findQuestions()
                .forEach(question -> assertEquals(question.getClass(), Question.class));
    }

    @Test
    @DisplayName("метод findQuestions кидает CsvReadException, если csv не прочитан")
    void getQuestionsShouldReturnIsEmptyList() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv("wrongFileName", questionConverter);
        assertThrows(CsvReadException.class, questionDao::findQuestions);
    }

}
