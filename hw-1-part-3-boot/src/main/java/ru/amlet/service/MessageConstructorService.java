package ru.amlet.service;

import ru.amlet.entity.Question;
import ru.amlet.entity.QuizState;

public interface MessageConstructorService {

    String createAnswerMessage(Question question);

    String createResultMessage(QuizState quizState);
}
