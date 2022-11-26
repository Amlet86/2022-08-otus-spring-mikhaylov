package ru.amlet.service;

import ru.amlet.entity.QuizState;

public interface ResultMessageConstructor {

    String createResultMessage(QuizState quizState);
}
