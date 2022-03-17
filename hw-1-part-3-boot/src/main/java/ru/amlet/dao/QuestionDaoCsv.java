package ru.amlet.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;
import ru.amlet.dto.QuestionDto;
import ru.amlet.entity.Question;
import ru.amlet.exception.CsvReadException;
import ru.amlet.service.LocalizationService;
import ru.amlet.utility.QuestionConverter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Repository
@ConfigurationProperties(prefix = "file")
public class QuestionDaoCsv implements QuestionDao {

    private final LocalizationService localizationService;
    private final QuestionConverter questionConverter;
    private final HashMap<String, String> name;

    public QuestionDaoCsv(LocalizationService localizationService,
                          QuestionConverter questionConverter,
                          HashMap<String, String> name) {
        this.localizationService = localizationService;
        this.questionConverter = questionConverter;
        this.name = name;
    }

    @Override
    public List<Question> findQuestions() {
        List<QuestionDto> questionsDto;
        Locale locale = localizationService.getLocale();
        String fileName = name.get(locale.toLanguageTag());
        InputStream inputStream = QuestionDaoCsv.class.getClassLoader().getResourceAsStream(fileName);
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
