package ru.amlet.service;

import ru.amlet.dto.Question;

public interface IOService {

    void printQuestion(Question question);

    String readAnswer();

    void printResult(String message);

}
