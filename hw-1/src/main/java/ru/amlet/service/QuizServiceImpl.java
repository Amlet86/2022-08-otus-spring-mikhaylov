package ru.amlet.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.Test;

import java.util.List;
import java.util.Objects;

@Service
public class QuizServiceImpl {

    private final IOService ioService;
    private final QuestionService questionService;
    private final int lowestPassingScore;

    public QuizServiceImpl(IOService ioService,
                           QuestionService questionService,
                           @Value("${lowest.passing.score}") int lowestPassingScore) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.lowestPassingScore = lowestPassingScore;
    }

    public void conducting() {
        Test test = new Test();
        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            ioService.printQuestion(question);
            String playersAnswer = ioService.readAnswer();
            if (question.getNumber() == 0) {
                test.getPlayer().setName(playersAnswer);
            } else {
                int tempScore = test.getScore() + countScore(question, playersAnswer);
                test.setScore(tempScore);
            }
        }
        boolean isWin = test.getScore() > lowestPassingScore;
        ioService.printResult(test, isWin);
    }

    private int countScore(Question question, String usersAnswer) {
        int result = 0;
        if (Objects.isNull(question.getAnswers()) &&
                StringUtils.equalsIgnoreCase("putin", usersAnswer)) {
            result = 1;
        } else {
            boolean isRight = question.getAnswers().stream()
                    .filter(answer -> StringUtils.equalsIgnoreCase(answer.getTextAnswer(), usersAnswer))
                    .map(Answer::isCorrect)
                    .findFirst()
                    .orElse(false);

            if (isRight) {
                result = 1;
            }
        }
        return result;
    }

}
