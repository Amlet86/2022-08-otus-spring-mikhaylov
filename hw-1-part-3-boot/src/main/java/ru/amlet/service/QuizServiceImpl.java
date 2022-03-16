package ru.amlet.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

import java.util.List;
import java.util.Objects;

@Service
public class QuizServiceImpl {

    private final IOServiceQuiz ioServiceQuiz;
    private final QuestionService questionService;
    private final int lowestPassingScore;

    public QuizServiceImpl(IOServiceQuiz ioServiceQuiz,
                           QuestionService questionService,
                           @Value("${lowest.passing.score}") int lowestPassingScore) {
        this.ioServiceQuiz = ioServiceQuiz;
        this.questionService = questionService;
        this.lowestPassingScore = lowestPassingScore;
    }

    public void conducting() {
        Quiz quiz = new Quiz(lowestPassingScore);
        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            ioServiceQuiz.putQuestion(question);
            String playersAnswer = ioServiceQuiz.getAnswer();
            if (question.getNumber() == 0) {
                quiz.getPlayer().setName(playersAnswer);
            } else {
                int tempScore = quiz.getScore() + countScore(question, playersAnswer);
                quiz.setScore(tempScore);
            }
        }
        ioServiceQuiz.putResult(quiz);
    }

    private int countScore(Question question, String usersAnswer) {
        int result = 0;
        if ((Objects.isNull(question.getAnswers()) || question.getAnswers().isEmpty()) &&
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
