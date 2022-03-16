package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.Quiz;

@Service
public class MessageConstructorImpl implements MessageConstructor {

    @Override
    public String createAnswerMessage(Question question) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (Answer answer : question.getAnswers()) {
            stringBuilder.append(i).append(".").append(answer.getTextAnswer()).append(" ");
            i++;
        }
        return String.valueOf(stringBuilder);
    }

    @Override
    public String createResultMessage(Quiz quiz) {
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
