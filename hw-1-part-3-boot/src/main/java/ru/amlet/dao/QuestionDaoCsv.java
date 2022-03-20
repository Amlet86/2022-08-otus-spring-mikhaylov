package ru.amlet.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Repository;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;
import ru.amlet.exception.CsvReadException;
import ru.amlet.utility.FileNameProvider;
import ru.amlet.utility.QuestionConverter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Repository
public class QuestionDaoCsv implements QuestionDao {

    private final QuestionConverter questionConverter;
    private final FileNameProvider fileNameProvider;

    public QuestionDaoCsv(QuestionConverter questionConverter,
                          FileNameProvider fileNameProvider) {
        this.questionConverter = questionConverter;
        this.fileNameProvider = fileNameProvider;
    }

    @Override
    public List<Question> findQuestions() {
        String fileName = fileNameProvider.getQuestionsFileName();

        List<QuestionDto> questionsDto;
        try (InputStream inputStream = QuestionDaoCsv.class.getClassLoader().getResourceAsStream(fileName);
             Reader reader = new InputStreamReader(inputStream)) {
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
