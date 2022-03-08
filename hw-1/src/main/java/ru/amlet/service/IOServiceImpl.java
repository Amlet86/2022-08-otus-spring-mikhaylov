package ru.amlet.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.amlet.dto.Question;

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
        if (StringUtils.isNotEmpty(question.getFirstAnswer())) {
            System.out.println(createAnswersLine(question));
        }
    }

    @Override
    public String readAnswer() {
        return scanner.nextLine();
    }

    private String createAnswersLine(Question question) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1. ").append(question.getFirstAnswer()).append(" ");
        stringBuilder.append("2. ").append(question.getSecondAnswer()).append(" ");
        stringBuilder.append("3. ").append(question.getThirdAnswer()).append(" ");
        return String.valueOf(stringBuilder);
    }

    @Override
    public void printResult(String message) {
        System.out.println(message);
    }
}
