package ru.amlet.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("Класс QuestionConverter")
public class QuestionConverterTest {

    @Autowired
    private QuestionConverter questionConverter;

    @Test
    @DisplayName("метод convertQuestion преобразует объект класса QuestionDto в Question")
    void shouldConvertQuestionDtoToQuestionClass() {
        QuestionDto questionDto = new QuestionDto();
        assertEquals(Question.class,
                questionConverter.convertQuestion(questionDto).getClass());
    }

    @Test
    @DisplayName("метод convertListQuestions преобразует объект класса QuestionDto в Question")
    void shouldConvertQuestionDtoListToQuestionClassList() {
        List<QuestionDto> questionDto = Collections.singletonList(new QuestionDto());
        questionConverter.convertListQuestions(questionDto)
                .forEach(question -> assertEquals(Question.class, question.getClass()));
    }
}
