package ru.amlet.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Question;
import ru.amlet.utility.CorrectAnswer;

import java.util.Arrays;
import java.util.Objects;

@Service
public class LeadingScoreServiceImpl implements LeadingScoreService {

    @Override
    public int countScore(Question question, Answer usersAnswer) {
        int result = 0;
        if ((Objects.isNull(question.getAnswers()) || question.getAnswers().isEmpty()) &&
                isInCorrectAnswers(usersAnswer.getTextAnswer())) {
            result = 1;
        } else {
            if (question.getAnswers().stream()
                    .filter(answer -> answer.equals(usersAnswer))
                    .map(Answer::isCorrect)
                    .findFirst()
                    .orElse(false)) {
                result = 1;
            }
        }
        return result;
    }

    private boolean isInCorrectAnswers(String usersAnswer) {
        return Arrays.stream(CorrectAnswer.values())
                .map(CorrectAnswer::getAnswer)
                .anyMatch(s -> StringUtils.equalsIgnoreCase(s, usersAnswer));

    }

}
