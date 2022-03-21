package ru.amlet.service;

import ru.amlet.entity.Question;
import ru.amlet.entity.QuizState;

public interface QuizService {

    void conducting();

    void askQuestion(Question question);

    String getAnswer();

    void outputResult(QuizState quizState);
}
