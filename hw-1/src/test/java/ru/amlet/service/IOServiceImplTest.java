package ru.amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.amlet.entity.Question;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Имплементация класса IOService")
public class IOServiceImplTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("метод printQuestion выводит на console текст переданный в него внутри объекта Question")
    void printQuestionShouldPutStringInConsole(){
        String questionsText = "questionsText";
        Question question = new Question();
        question.setQuestion(questionsText);
        IOServiceImpl ioService = new IOServiceImpl();
        ioService.printQuestion(question);
        assertEquals(questionsText, outputStreamCaptor.toString());
    }
}
