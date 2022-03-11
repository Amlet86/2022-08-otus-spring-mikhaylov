package ru.amlet.service;

import ru.amlet.entity.Question;
import ru.amlet.entity.Test;

public interface IOService {

    void printQuestion(Question question);

    String readAnswer();

    void printResult(Test test, boolean isWin);

}
