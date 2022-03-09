package ru.amlet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.amlet.entity.Player;
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

    @Test
    @DisplayName("метод printResult создаёт сообщение о положительном результате и выводит его на console")
    void printResultShouldCreatePositiveMessageAndPutInConsole(){
        Player player = new Player();
        player.setName("Andrey");
        player.setScore(5);
        IOServiceImpl ioService = new IOServiceImpl();
        ioService.printResult(player, true);
        assertEquals("Dear Andrey your result: 5 it's good result. Congratulation!\n",
                outputStreamCaptor.toString());
    }

    @Test
    @DisplayName("метод printResult создаёт сообщение об отрицательном результате и выводит его на console")
    void printResultShouldCreateNegativeMessageAndPutInConsole(){
        Player player = new Player();
        player.setName("Andrey");
        player.setScore(1);
        IOServiceImpl ioService = new IOServiceImpl();
        ioService.printResult(player, false);
        assertEquals("Dear Andrey your result: 1 it's a terrible result. Try again.\n",
                outputStreamCaptor.toString());
    }
}
