package ru.amlet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.amlet.dao.QuestionDao;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;
import ru.amlet.utility.QuestionsHandler;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public List<QuestionDto> getQuestionsDto() {
        return dao.findQuestions();
    }

    public List<Question> getQuestions() {
        List<QuestionDto> questionDtoList = dao.findQuestions();
        return convertQuestionFromQuestionDto(questionDtoList);
    }

    public Question convertQuestionFromQuestionDto(QuestionDto questionDto) {
        return QuestionsHandler.handleQuestion(questionDto);
    }

    public List<Question> convertQuestionFromQuestionDto(List<QuestionDto> questionsDto) {
        List<Question> list = new ArrayList<>();
        questionsDto.forEach(questionDto -> {
            list.add(convertQuestionFromQuestionDto(questionDto));
        });
        return list;
    }

}
