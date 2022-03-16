package ru.amlet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.amlet.dao.QuestionDao;
import ru.amlet.entity.Question;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public List<Question> getQuestions() {
        return dao.findQuestions();
    }

}
