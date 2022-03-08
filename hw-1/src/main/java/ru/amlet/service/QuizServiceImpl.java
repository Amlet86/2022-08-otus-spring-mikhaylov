package ru.amlet.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.dto.Player;
import ru.amlet.dto.Question;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl {

    private final IOService ioService;
    private final QuestionServiceImpl questionService;
    private Player player;
    @Value("${not.less}")
    private int notLess;

    public void conducting() {
        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            ioService.printQuestion(question);
            String answer = ioService.readAnswer();
            if (question.getNumber() == 0) {
                player.setName(answer);
            } else {
                countScore(question, answer);
            }
        }
        ioService.printResult(createResultMessage());
    }

    private void countScore(Question question, String answer) {
        try {
            if (whatAnswerIsRight(question) == Integer.parseInt(answer)) {
                player.setScore(player.getScore() + 1);
            }
        } catch (Exception e) {
            if (StringUtils.equalsIgnoreCase("putin", answer)) {
                player.setScore(player.getScore() + 1);
            }
            e.getMessage();
        }
    }

    private int whatAnswerIsRight(Question question) {
        int result = 0;
        if (question.isFirstCorrect()) {
            result = 1;
        }
        if (question.isSecondCorrect()) {
            result = 2;
        }
        if (question.isThirdCorrect()) {
            result = 3;
        }
        return result;
    }

    private String createResultMessage() {
        String resultMessage = "Dear " + player.getName() + " your result: " + player.getScore();
        if (player.getScore() > notLess) {
            resultMessage = resultMessage + " it's good result. Congratulation!";
        } else {
            resultMessage = resultMessage + " it's a terrible result. Try again.";
        }
        return resultMessage;
    }

}
