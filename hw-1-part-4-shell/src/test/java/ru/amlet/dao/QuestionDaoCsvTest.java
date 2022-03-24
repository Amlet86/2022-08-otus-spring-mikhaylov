package ru.amlet.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.amlet.entity.Question;
import ru.amlet.exception.CsvReadException;
import ru.amlet.utility.FileNameProvider;
import ru.amlet.utility.QuestionConverter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Имплементация класса QuestionDao")
public class QuestionDaoCsvTest {

    @Autowired
    private QuestionConverter questionConverter;
    @Mock
    private FileNameProvider fileNameProvider;

    private QuestionDaoCsv questionDao;

    @BeforeEach
    void setUp() {
        questionConverter = new QuestionConverter();
        questionDao = new QuestionDaoCsv(questionConverter, fileNameProvider);
    }

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
