package ru.amlet.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.QuizState;
import ru.amlet.service.*;

import java.util.List;
import java.util.Objects;

@Component
@ShellComponent
public class QuizControllerImpl {

    private final IOService ioService;
    private final GreetingService greetingService;
    private final QuestionService questionService;
    private final MessageConstructorService messageConstructorService;
    private final LeadingScoreService leadingScoreService;
    private final int lowestPassingScore;

    public QuizControllerImpl(IOService ioService,
                              GreetingService greetingService,
                              QuestionService questionService,
                              MessageConstructorService messageConstructorService,
                              LeadingScoreService leadingScoreService,
                              @Value("${lowest.passing.score}") int lowestPassingScore) {
        this.ioService = ioService;
        this.greetingService = greetingService;
        this.questionService = questionService;
        this.messageConstructorService = messageConstructorService;
        this.leadingScoreService = leadingScoreService;
        this.lowestPassingScore = lowestPassingScore;
    }

    @ShellMethod(value = "Start quiz", key = {"s", "start"})
    public void startQuiz(){
        conducting();
    }

    private void conducting() {
        Player player = greetingService.greetingAndAcquaintance();
        QuizState quizState = new QuizState(player, lowestPassingScore);

        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            askQuestion(question);
            Answer playersAnswer = new Answer(getAnswer());
            quizState.incrementScore(leadingScoreService.countScore(question, playersAnswer));
        }
        outputResult(quizState);
    }

    private void askQuestion(Question question) {
        ioService.writeString(question.getQuestion());
        if (Objects.nonNull(question.getAnswers()) &&
                !question.getAnswers().isEmpty()) {
            String questionText = messageConstructorService.createAnswerMessage(question);
            ioService.writeString(questionText);
        }
    }

    private String getAnswer() {
        return ioService.readString();
    }

    private void outputResult(QuizState quizState) {
        String resultText = messageConstructorService.createResultMessage(quizState);
        ioService.writeString(resultText);
    }

}
