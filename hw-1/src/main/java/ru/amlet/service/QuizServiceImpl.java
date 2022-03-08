package ru.amlet.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Player;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl {

    private final IOService ioService;
    private final QuestionServiceImpl questionService;
    private final Player player;
    @Value("${not.less}")
    private int notLess;

    public void conducting() {
        List<Question> questions = questionService.getQuestions();
        for (Question question : questions) {
            ioService.printQuestion(question);
            String playersAnswer = ioService.readAnswer();
            if (question.getNumber() == 0) {
                player.setName(playersAnswer);
            } else {
                countScore(question, playersAnswer);
            }
        }
        ioService.printResult(player, player.getScore() > notLess);
    }

    private void countScore(Question question, String answer) {
        if (Objects.isNull(question.getAnswers()) &&
                StringUtils.equalsIgnoreCase("putin", answer)) {
            player.setScore(player.getScore() + 1);
        } else {
            Object isRight = question.getAnswers().get(answer);
            if (Objects.nonNull(isRight) && (Boolean) isRight) {
                player.setScore(player.getScore() + 1);
            }
        }
    }

}
