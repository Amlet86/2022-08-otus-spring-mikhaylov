package ru.amlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.QuizState;

import java.util.List;
import java.util.Objects;

@Service
public class QuizServiceImpl implements QuizService {

    private final IOService ioService;
    private final GreetingService greetingService;
    private final QuestionService questionService;
    private final MessageConstructorService messageConstructorService;
    private final LeadingScoreService leadingScoreService;
    private final int lowestPassingScore;

    public QuizServiceImpl(IOService ioService,
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

    @Override
    public void conducting() {
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

    @Override
    public void askQuestion(Question question) {
        ioService.writeString(question.getQuestion());
        if (Objects.nonNull(question.getAnswers()) &&
                !question.getAnswers().isEmpty()) {
            String questionText = messageConstructorService.createAnswerMessage(question);
            ioService.writeString(questionText);
        }
    }

    @Override
    public String getAnswer() {
        return ioService.readString();
    }

    @Override
    public void outputResult(QuizState quizState) {
        String resultText = messageConstructorService.createResultMessage(quizState);
        ioService.writeString(resultText);
    }

}
