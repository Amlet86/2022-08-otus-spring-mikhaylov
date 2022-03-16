package ru.amlet.service;

import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

public interface QuizService {

    void conducting();

    void askQuestion(Question question);

    String getAnswer();

    void outputResult(Quiz quiz);
}
