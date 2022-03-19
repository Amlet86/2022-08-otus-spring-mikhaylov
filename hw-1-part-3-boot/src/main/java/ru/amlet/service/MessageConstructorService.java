package ru.amlet.service;

import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

public interface MessageConstructorService {

    String createAnswerMessage(Question question);

    String createResultMessage(Quiz quiz);
}
