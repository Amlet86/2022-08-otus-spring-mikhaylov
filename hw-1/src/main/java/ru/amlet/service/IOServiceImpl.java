package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

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
    public void printResult(Quiz quiz) {
        System.out.println(createResultMessage(quiz));
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
