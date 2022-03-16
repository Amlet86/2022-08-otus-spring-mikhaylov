package ru.amlet.service;

import ru.amlet.entity.Answer;
import ru.amlet.entity.Question;

public interface LeadingScoreService {

    int countScore(Question question, Answer usersAnswer);
}
