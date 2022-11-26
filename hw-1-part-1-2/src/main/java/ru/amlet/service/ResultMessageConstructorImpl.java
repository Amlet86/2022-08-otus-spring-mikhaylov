package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Player;
import ru.amlet.entity.QuizState;

@Service
public class ResultMessageConstructorImpl implements ResultMessageConstructor {

    @Override
    public String createResultMessage(QuizState quizState) {
        Player player = quizState.getPlayer();
        String resultMessage = "Dear " + player.getName() + " your result: " + quizState.getScore();
        if (quizState.isWin()) {
            resultMessage = resultMessage + " it's a good result. Congratulation!";
        } else {
            resultMessage = resultMessage + " it's a terrible result. Try again.";
        }
        return resultMessage;
    }

}
