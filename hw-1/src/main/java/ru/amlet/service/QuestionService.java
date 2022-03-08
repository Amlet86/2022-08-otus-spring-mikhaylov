package ru.amlet.service;

import ru.amlet.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    List<QuestionDto> getQuestionsDto();
}
