package ru.amlet.service;

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
    private final MessageConstructor messageConstructor;
    private final LeadingScoreService leadingScoreService;
    private final int lowestPassingScore;
    private final String greetingAndAcquaintance;

    public QuizServiceImpl(IOService ioService,
                           QuestionService questionService,
                           MessageConstructor messageConstructor,
                           LeadingScoreService leadingScoreService,
                           @Value("${lowest.passing.score}") int lowestPassingScore,
                           @Value("${greeting.acquaintance}") String greetingAndAcquaintance) {
        this.ioService = ioService;
        this.questionService = questionService;
        this.messageConstructor = messageConstructor;
        this.leadingScoreService = leadingScoreService;
        this.lowestPassingScore = lowestPassingScore;
        this.greetingAndAcquaintance = greetingAndAcquaintance;
    }

    @Override
    public void conducting() {
        Player player = greetingAndAcquaintance();
        Quiz quiz = new Quiz(player, lowestPassingScore);

        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            askQuestion(question);
            Answer playersAnswer = new Answer(getAnswer());
            quiz.incrementScore(leadingScoreService.countScore(question, playersAnswer));
        }
        outputResult(quiz);
    }

    private Player greetingAndAcquaintance() {
        ioService.writeString(greetingAndAcquaintance);
        String name = getAnswer();
        return new Player(name);
    }

    @Override
    public void askQuestion(Question question) {
        ioService.writeString(question.getQuestion());
        if (Objects.nonNull(question.getAnswers()) &&
                !question.getAnswers().isEmpty()) {
            String questionText = messageConstructor.createAnswerMessage(question);
            ioService.writeString(questionText);
        }
    }

    @Override
    public String getAnswer() {
        return ioService.readString();
    }

    @Override
    public void outputResult(Quiz quiz) {
        String resultText = messageConstructor.createResultMessage(quiz);
        ioService.writeString(resultText);
    }

}
