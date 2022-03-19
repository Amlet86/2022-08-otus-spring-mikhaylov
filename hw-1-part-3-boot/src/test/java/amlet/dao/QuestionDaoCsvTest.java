package amlet.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import ru.amlet.dao.QuestionDaoCsv;
import ru.amlet.entity.Question;
import ru.amlet.exception.CsvReadException;
import ru.amlet.service.LocalizationService;
import ru.amlet.utility.QuestionConverter;

import java.util.HashMap;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Имплементация класса QuestionDao")
public class QuestionDaoCsvTest {

    @Mock
    private LocalizationService localizationService;
    @Autowired
    QuestionConverter questionConverter;

    QuestionDaoCsv questionDao;

    @BeforeEach
    void setUp() {
        questionConverter = new QuestionConverter();
        HashMap<String, String> name = new HashMap<>();
        name.put("en", "qa_en.csv");
        name.put("ru", "qa_ru.csv");
        questionDao = new QuestionDaoCsv(localizationService, questionConverter, name);
    }

    @Test
    @DisplayName("метод findQuestions, прочитав qa_en.csv, возвращает не пустой список")
    void findEnQuestionsShouldReturnIsNotEmptyList() {
        given(localizationService.getLocale())
                .willReturn(Locale.ENGLISH);
        assertFalse(questionDao.findQuestions().isEmpty());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав qa_ru.csv, возвращает не пустой список")
    void findRuQuestionsShouldReturnIsNotEmptyList() {
        given(localizationService.getLocale())
                .willReturn(new Locale("ru"));
        assertFalse(questionDao.findQuestions().isEmpty());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав qa_en.csv, возвращает список из 5 объектов")
    void findEnQuestionsShouldReturnListWithSixElements() {
        given(localizationService.getLocale())
                .willReturn(Locale.ENGLISH);
        assertEquals(5, questionDao.findQuestions().size());
    }

    @Test
    @DisplayName("метод findQuestions, прочитав qa_ru.csv, возвращает список из 5 объектов")
    void findRuQuestionsShouldReturnListWithSixElements() {
        given(localizationService.getLocale())
                .willReturn(new Locale("ru"));
        assertEquals(5, questionDao.findQuestions().size());
    }

    @Test
    @DisplayName("метод findQuestions, CsvReadException, если csv не прочитан")
    void findFrQuestionsShouldThrowCsvReadException() {
        given(localizationService.getLocale())
                .willReturn(Locale.FRANCE);
        assertThrows(CsvReadException.class, questionDao::findQuestions);
    }

    @Test
    @DisplayName("метод findQuestions, прочитав csv, возвращает список из объектов класса Question")
    void findQuestionsShouldReturnOnlyQuestionClasses() {
        given(localizationService.getLocale())
                .willReturn(new Locale("ru"));
        questionDao.findQuestions()
                .forEach(question -> assertEquals(Question.class, question.getClass()));
    }

}
