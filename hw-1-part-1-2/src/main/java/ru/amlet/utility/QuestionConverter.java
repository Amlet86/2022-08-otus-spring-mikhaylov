package ru.amlet.utility;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Question;

import java.util.*;

@Component
public class QuestionConverter {

    public List<Question> convertListQuestions(List<QuestionDto> questionsDto) {
        List<Question> list = new ArrayList<>();
        questionsDto.forEach(questionDto -> {
            list.add(convertQuestion(questionDto));
        });
        return list;
    }

    public Question convertQuestion(QuestionDto questionDto) {
        Question question = new Question();
        question.setNumber(questionDto.getNumber());
        question.setQuestion(questionDto.getQuestion());
        if(StringUtils.isNotEmpty(questionDto.getFirstAnswer())) {
            question.getAnswers().add(new Answer(questionDto.getFirstAnswer(), questionDto.isFirstCorrect()));
            question.getAnswers().add(new Answer(questionDto.getSecondAnswer(), questionDto.isSecondCorrect()));
            question.getAnswers().add(new Answer(questionDto.getThirdAnswer(), questionDto.isThirdCorrect()));
        }
        return question;
    }



}
