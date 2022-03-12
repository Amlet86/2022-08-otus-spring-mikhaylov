package ru.amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

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
        Question question = new Question();
        question.setQuestion("questionsText");
        IOServiceImpl ioService = new IOServiceImpl();
        ioService.printQuestion(question);
        assertEquals("questionsText\n", outputStreamCaptor.toString());
    }

    @Test
    @DisplayName("метод printResult создаёт сообщение о положительном результате и выводит его на console")
    void printResultShouldCreatePositiveMessageAndPutInConsole(){
        Quiz quiz = new Quiz(3);
        quiz.getPlayer().setName("Andrey");
        quiz.setScore(5);
        IOServiceImpl ioService = new IOServiceImpl();
        ioService.printResult(quiz);
        assertEquals("Dear Andrey your result: 5 it's good result. Congratulation!\n",
                outputStreamCaptor.toString());
    }

    @Test
    @DisplayName("метод printResult создаёт сообщение об отрицательном результате и выводит его на console")
    void printResultShouldCreateNegativeMessageAndPutInConsole(){
        Quiz quiz = new Quiz(3);
        quiz.getPlayer().setName("Andrey");
        quiz.setScore(1);
        IOServiceImpl ioService = new IOServiceImpl();
        ioService.printResult(quiz);
        assertEquals("Dear Andrey your result: 1 it's a terrible result. Try again.\n",
                outputStreamCaptor.toString());
    }
}
