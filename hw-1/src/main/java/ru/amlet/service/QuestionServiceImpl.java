package ru.amlet.service;

import ru.amlet.dao.QuestionDao;
import ru.amlet.dto.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    public List<Question> getQuestions() {
        return dao.findQuestions();
    }
}
