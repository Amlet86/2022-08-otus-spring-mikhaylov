package ru.amlet.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.amlet.dto.QuestionDto;
import ru.amlet.exception.CsvReadException;

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
    public List<QuestionDto> findQuestions() {
        List<QuestionDto> questionDtos;
        URL url = QuestionDaoCsv.class.getClassLoader().getResource(name);
        try (FileReader fileReader = new FileReader(url.getPath())) {
            questionDtos = new CsvToBeanBuilder(fileReader)
                    .withType(QuestionDto.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            throw new CsvReadException(e.getMessage());
        }
        return questionDtos;
    }
}
