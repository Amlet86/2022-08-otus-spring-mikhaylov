package ru.amlet.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.amlet.dto.Question;
import ru.amlet.exception.ShitHappensException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final String name;

    public QuestionDaoCsv(@Value("${file.name}") String name) {
        this.name = name;
    }

    @Override
    public List<Question> findQuestions() {
        List<Question> questions;
        URL url = QuestionDaoCsv.class.getClassLoader().getResource(name);
        try {
            questions = new CsvToBeanBuilder(new FileReader(url.getPath()))
                    .withType(Question.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            throw new ShitHappensException(e.getMessage());
        }
        return questions;
    }
}
