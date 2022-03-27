package ru.amlet.dao;

import ru.amlet.entity.Question;

import java.util.List;

public interface QuestionDao {

    List<Question> findQuestions();
}
