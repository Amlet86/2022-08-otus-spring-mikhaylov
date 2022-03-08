package ru.amlet.dao;

import ru.amlet.dto.QuestionDto;

import java.util.List;

public interface QuestionDao {

    List<QuestionDto> findQuestions();
}
