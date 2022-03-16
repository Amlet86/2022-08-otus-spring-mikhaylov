package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;

@Service
public class IOServiceQuizImpl implements IOServiceQuiz, IOService {

    private final Scanner input;
    private final PrintStream output;

    public IOServiceQuizImpl(InputStream inputStream, PrintStream outputStream) {
        this.input = new Scanner(inputStream);
        this.output = outputStream;
    }

    @Override
    public void writeString(String s) {
        output.println(s);
    }

    @Override
    public String readString() {
        return input.nextLine();
    }

    @Override
    public void putQuestion(Question question) {
        writeString(question.getQuestion());
        if (Objects.nonNull(question.getAnswers()) &&
                !question.getAnswers().isEmpty()) {
            writeString(createAnswersLine(question));
        }
    }

    @Override
    public String getAnswer() {
        return readString();
    }

    private String createAnswersLine(Question question) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (Answer answer : question.getAnswers()) {
            stringBuilder.append(i).append(".").append(answer.getTextAnswer()).append(" ");
            i++;
        }
        return String.valueOf(stringBuilder);
    }

    @Override
    public void putResult(Quiz quiz) {
        writeString(createResultMessage(quiz));
    }

    private String createResultMessage(Quiz quiz) {
        Player player = quiz.getPlayer();
        String resultMessage = "Dear " + player.getName() + " your result: " + quiz.getScore();
        if (quiz.isWin()) {
            resultMessage = resultMessage + " it's a good result. Congratulation!";
        } else {
            resultMessage = resultMessage + " it's a terrible result. Try again.";
        }
        return resultMessage;
    }
}
