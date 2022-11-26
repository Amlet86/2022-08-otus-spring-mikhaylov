package ru.amlet.service;

import org.springframework.stereotype.Service;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Question;

@Service
public class AnswerMessageConstructorImpl implements AnswerMessageConstructor {

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

}
