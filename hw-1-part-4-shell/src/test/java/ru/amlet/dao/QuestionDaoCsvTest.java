package ru.amlet.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.amlet.entity.Question;
import ru.amlet.exception.CsvReadException;
import ru.amlet.utility.FileNameProvider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Имплементация класса QuestionDao")
public class QuestionDaoCsvTest {

    @MockBean
    private FileNameProvider fileNameProvider;
    @Autowired
    private QuestionDaoCsv questionDao;

    @Test
    @DisplayName("метод findQuestions, прочитав qa_en.csv, возвращает не пустой список")
    void findEnQuestionsShouldReturnIsNotEmptyList() {
        given(fileNameProvider.getQuestionsFileName())
                .willReturn("qa_en.csv");
        assertFalse(questionDao.findQuestions().isEmpty());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав qa_ru.csv, возвращает не пустой список")
    void findRuQuestionsShouldReturnIsNotEmptyList() {
        given(fileNameProvider.getQuestionsFileName())
                .willReturn("qa_ru.csv");
        assertFalse(questionDao.findQuestions().isEmpty());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав qa_en.csv, возвращает список из 5 объектов")
    void findEnQuestionsShouldReturnListWithSixElements() {
        given(fileNameProvider.getQuestionsFileName())
                .willReturn("qa_en.csv");
        assertEquals(5, questionDao.findQuestions().size());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав qa_ru.csv, возвращает список из 5 объектов")
    void findRuQuestionsShouldReturnListWithSixElements() {
        given(fileNameProvider.getQuestionsFileName())
                .willReturn("qa_ru.csv");
        assertEquals(5, questionDao.findQuestions().size());
    }

    @Test
    @DisplayName("метод findQuestions, CsvReadException, если csv не прочитан")
    void findFrQuestionsShouldThrowCsvReadException() {
        given(fileNameProvider.getQuestionsFileName())
                .willReturn("qa_fr.csv");
        assertThrows(CsvReadException.class, questionDao::findQuestions);
    }

    @Test
    @DisplayName("метод findQuestions, прочитав csv, возвращает список из объектов класса Question")
    void findQuestionsShouldReturnOnlyQuestionClasses() {
        given(fileNameProvider.getQuestionsFileName())
                .willReturn("qa_ru.csv");
        questionDao.findQuestions()
                .forEach(question -> assertEquals(Question.class, question.getClass()));
    }

}
