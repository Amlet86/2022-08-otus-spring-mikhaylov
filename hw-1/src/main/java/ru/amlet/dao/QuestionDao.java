package ru.amlet.dao;

import ru.amlet.dto.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findQuestions();
}
