package ru.amlet.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

import java.util.List;
import java.util.Objects;

@Service
public class QuizServiceImpl implements QuizService {

    private final IOService ioService;
    private final QuestionService questionService;
    private final int lowestPassingScore;
    private final String greetingAndAcquaintance;

    public QuizServiceImpl(IOService ioService,
                           QuestionService questionService,
                           @Value("${lowest.passing.score}") int lowestPassingScore,
                           @Value("${greeting.acquaintance}") String greetingAndAcquaintance) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.lowestPassingScore = lowestPassingScore;
        this.greetingAndAcquaintance = greetingAndAcquaintance;
    }

    @Override
    public void conducting() {
        Quiz quiz = new Quiz(lowestPassingScore);
        String playersName = greetingAndAcquaintance();
        quiz.getPlayer().setName(playersName);

        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            putQuestion(question);
            String playersAnswer = getAnswer();
            int tempScore = quiz.getScore() + countScore(question, playersAnswer);
            quiz.setScore(tempScore);
        }
        putResult(quiz);
    }

    private String greetingAndAcquaintance() {
        ioService.writeString(greetingAndAcquaintance);
        return getAnswer();
    }

    @Override
    public void putQuestion(Question question) {
        ioService.writeString(question.getQuestion());
        if (Objects.nonNull(question.getAnswers()) &&
                !question.getAnswers().isEmpty()) {
            ioService.writeString(createAnswersLine(question));
        }
    }

    @Override
    public String getAnswer() {
        return ioService.readString();
    }

    private String createAnswersLine(Question question) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (Answer answer : question.getAnswers()) {
            stringBuilder.append(i).append(".").append(answer.getTextAnswer()).append(" ");
            i++;
        }
        return String.valueOf(stringBuilder);
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

    @Override
    public void putResult(Quiz quiz) {
        ioService.writeString(createResultMessage(quiz));
    }

    private String createResultMessage(Quiz quiz) {
        Player player = quiz.getPlayer();
        String resultMessage = "Dear " + player.getName() + " your result: " + quiz.getScore();
        if (quiz.isWin()) {
            resultMessage = resultMessage + " it's a good result. Congratulation!";
        } else {
            resultMessage = resultMessage + " it's a terrible result. Try again.";
        }
        return resultMessage;
    }

}
