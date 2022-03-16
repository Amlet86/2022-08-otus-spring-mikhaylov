package ru.amlet.service;

import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

public interface IOServiceQuiz {

    void putQuestion(Question question);

    String getAnswer();

    void putResult(Quiz quiz);

}
