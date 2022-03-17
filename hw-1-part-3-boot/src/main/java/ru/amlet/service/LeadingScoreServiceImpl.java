package ru.amlet.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Question;

import java.util.Objects;

@Service
public class LeadingScoreServiceImpl implements LeadingScoreService {

    private final static String ALWAYS_CORRECT_EN = "Putin";
    private final static String ALWAYS_CORRECT_RU = "Путин";

    @Override
    public int countScore(Question question, Answer usersAnswer) {
        int result = 0;
        if ((Objects.isNull(question.getAnswers()) || question.getAnswers().isEmpty()) &&
                (StringUtils.equalsIgnoreCase(ALWAYS_CORRECT_EN, usersAnswer.getTextAnswer()) ||
                        StringUtils.equalsIgnoreCase(ALWAYS_CORRECT_RU, usersAnswer.getTextAnswer()))) {
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

}
