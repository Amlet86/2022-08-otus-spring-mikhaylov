package ru.amlet.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс QuestionConverter")
public class QuestionConverterTest {

    private final QuestionConverter questionConverter = new QuestionConverter();

    @Test
    @DisplayName("метод convertQuestionFromQuestionDto преобразует объект класса QuestionDto в Question")
    void shouldConvertQuestionDtoToQuestionClass() {
        QuestionDto questionDto = new QuestionDto();
        assertEquals(Question.class,
                questionConverter.convertQuestion(questionDto).getClass());
    }
}
