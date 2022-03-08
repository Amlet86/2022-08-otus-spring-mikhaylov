package ru.amlet.utility;

import org.apache.commons.lang3.StringUtils;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;

import java.util.HashMap;
import java.util.Map;

public class QuestionsHandler {

    public static Question handleQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setNumber(questionDto.getNumber());
        question.setQuestion(questionDto.getQuestion());
        if(StringUtils.isNotEmpty(questionDto.getFirstAnswer())) {
            Map answers = new HashMap();
            answers.put(questionDto.getFirstAnswer(), questionDto.isFirstCorrect());
            answers.put(questionDto.getSecondAnswer(), questionDto.isSecondCorrect());
            answers.put(questionDto.getThirdAnswer(), questionDto.isThirdCorrect());
            question.setAnswers(answers);
        }
        return question;
    }
}
