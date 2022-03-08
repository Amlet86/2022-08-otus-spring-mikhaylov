package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;

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
        if (Objects.nonNull(question.getAnswers())) {
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
        for (Object answer : question.getAnswers().keySet()) {
            stringBuilder.append(i + ".").append(answer).append(" ");
            i++;
        }
        return String.valueOf(stringBuilder);
    }

    @Override
    public void printResult(Player player, boolean isWin) {
        System.out.println(createResultMessage(player, isWin));
    }

    private String createResultMessage(Player player, boolean isWin) {
        String resultMessage = "Dear " + player.getName() + " your result: " + player.getScore();
        if (isWin) {
            resultMessage = resultMessage + " it's good result. Congratulation!";
        } else {
            resultMessage = resultMessage + " it's a terrible result. Try again.";
        }
        return resultMessage;
    }

}
