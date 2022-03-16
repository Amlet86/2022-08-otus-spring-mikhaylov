package ru.amlet.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;
import ru.amlet.exception.CsvReadException;
import ru.amlet.utility.QuestionConverter;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final String name;
    private final QuestionConverter questionConverter;

    public QuestionDaoCsv(@Value("${file.name}") String name, QuestionConverter questionConverter) {
        this.name = name;
        this.questionConverter = questionConverter;
    }

    @Override
    public List<Question> findQuestions() {
        List<QuestionDto> questionsDto;
        InputStream inputStream = QuestionDaoCsv.class.getClassLoader().getResourceAsStream(name);
        try (Reader reader = new InputStreamReader(inputStream)) {
            questionsDto = new CsvToBeanBuilder<QuestionDto>(reader)
                    .withType(QuestionDto.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new CsvReadException(e.getMessage());
        }
        return questionConverter.convertListQuestions(questionsDto);
    }

}
