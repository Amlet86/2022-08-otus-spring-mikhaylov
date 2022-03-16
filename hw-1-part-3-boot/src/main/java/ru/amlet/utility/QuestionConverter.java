package ru.amlet.utility;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Answer;
import ru.amlet.entity.Question;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionConverter {

    private final QuizLocalization quizLocalization;

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
        String localQuestion = quizLocalization.propertyLocalization(questionDto.getQuestion());
        question.setQuestion(localQuestion);
        if (StringUtils.isNotEmpty(questionDto.getFirstAnswer())) {
            question.getAnswers().add(
                    new Answer(quizLocalization.propertyLocalization(questionDto.getQuestion() + "." + questionDto.getFirstAnswer()), questionDto.isFirstCorrect()));
            question.getAnswers().add(
                    new Answer(quizLocalization.propertyLocalization(questionDto.getQuestion() + "." + questionDto.getSecondAnswer()), questionDto.isSecondCorrect()));
            question.getAnswers().add(
                    new Answer(quizLocalization.propertyLocalization(questionDto.getQuestion() + "." + questionDto.getThirdAnswer()), questionDto.isThirdCorrect()));
        }
        return question;
    }

}
