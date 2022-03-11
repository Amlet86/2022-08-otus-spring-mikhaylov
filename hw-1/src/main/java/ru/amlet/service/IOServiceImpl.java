package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.Test;

import java.util.Objects;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final Scanner scanner;

    public IOServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void printQuestion(Question question) {
        System.out.println(question.getQuestion());
        if (Objects.nonNull(question.getAnswers()) &&
                !question.getAnswers().isEmpty()) {
            System.out.println(createAnswersLine(question));
        }
    }

    @Override
    public String readAnswer() {
        return scanner.nextLine();
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
    public void printResult(Test test, boolean isWin) {
        System.out.println(createResultMessage(test, isWin));
    }

    private String createResultMessage(Test test, boolean isWin) {
        Player player = test.getPlayer();
        int score = test.getScore();
        String resultMessage = "Dear " + player.getName() + " your result: " + score;
        if (isWin) {
            resultMessage = resultMessage + " it's good result. Congratulation!";
        } else {
            resultMessage = resultMessage + " it's a terrible result. Try again.";
        }
        return resultMessage;
    }

}
