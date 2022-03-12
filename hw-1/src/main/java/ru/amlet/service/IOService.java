package ru.amlet.service;

import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

public interface IOService {

    void printQuestion(Question question);

    String readAnswer();

    void printResult(Quiz quiz);

}
