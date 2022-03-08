package ru.amlet.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.amlet.dto.Question;
import ru.amlet.exception.ShitHappensException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Имплементация класса QuestionDao")
public class QuestionDaoCsvTest {

    @Test
    @DisplayName("метод findQuestions возвращает не пустой список, если csv прочитан")
    void getQuestionsShouldReturnIsNotEmptyList() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv("qa.csv");
        List<Question> questions = questionDao.findQuestions();
        assertFalse(questions.isEmpty());
    }

    @Test
    @DisplayName("метод findQuestions кидает исключение, если csv не прочитан")
    void getQuestionsShouldReturnIsEmptyList() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv("");
        assertThrows(ShitHappensException.class, questionDao::findQuestions);
    }

}
