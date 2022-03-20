package ru.amlet.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Player;
import ru.amlet.entity.Question;
import ru.amlet.entity.QuizState;

@Service
public class MessageConstructorServiceImpl implements MessageConstructorService {

    private final BundleService bundleService;
    private final String bundleWin;
    private final String bundleLose;

    public MessageConstructorServiceImpl(BundleService bundleService,
                                         @Value("${bundle.result.win}") String bundleWin,
                                         @Value("${bundle.result.lose}") String bundleLose) {
        this.bundleService = bundleService;
        this.bundleWin = bundleWin;
        this.bundleLose = bundleLose;
    }

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
    public String createResultMessage(QuizState quizState) {
        Player player = quizState.getPlayer();
        String resultMessage;
        if (quizState.isWin()) {
            String bundleMessage = bundleService.getBundleObject(bundleWin);
            resultMessage = String.format(bundleMessage, player.getName(), quizState.getScore());
        } else {
            String bundleMessage = bundleService.getBundleObject(bundleLose);
            resultMessage = String.format(bundleMessage, player.getName(), quizState.getScore());
        }
        return resultMessage;
    }

}
