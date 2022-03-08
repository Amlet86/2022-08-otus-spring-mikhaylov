package ru.amlet.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.amlet.dto.QuestionDto;
import ru.amlet.exception.CsvReadException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Имплементация класса QuestionDao")
public class QuestionDaoCsvTest {

    private final String csvName = "qa.csv";

    @Test
    @DisplayName("метод findQuestions, прочитав csv, возвращает не пустой список")
    void getQuestionsShouldReturnIsNotEmptyList() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName);
        assertFalse(questionDao.findQuestions().isEmpty());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав csv, возвращает список из 6 объектов")
    void csvFileShouldContainsSixQuestions() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName);
        assertEquals(6, questionDao.findQuestions().size());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав csv, возвращает список из объектов класса Question")
    void csvFileShouldContainsOnlyQuestionClasses() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv(csvName);
        questionDao.findQuestions()
                .forEach(question -> assertEquals(question.getClass(), QuestionDto.class));
    }

    @Test
    @DisplayName("метод findQuestions кидает исключение, если csv не прочитан")
    void getQuestionsShouldReturnIsEmptyList() {
        QuestionDaoCsv questionDao = new QuestionDaoCsv("");
        assertThrows(CsvReadException.class, questionDao::findQuestions);
    }

}
