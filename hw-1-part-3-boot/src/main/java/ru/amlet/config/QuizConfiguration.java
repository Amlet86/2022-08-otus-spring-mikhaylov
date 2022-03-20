package ru.amlet.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.amlet.service.IOService;
import ru.amlet.service.IOServiceImpl;
import ru.amlet.service.LocalizationService;
import ru.amlet.service.LocalizationServiceImpl;
import ru.amlet.utility.FileNameProvider;
import ru.amlet.utility.FileNameProviderImpl;

import java.util.HashMap;
import java.util.Locale;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file")
public class QuizConfiguration {

    @Value("${locale}")
    private String locale;

    private HashMap<String, String> name;

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }

    @Bean
    public LocalizationService localizationService() {
        return new LocalizationServiceImpl(new Locale(locale));
    }

    @Bean
    public FileNameProvider fileNameProvider() {
        return new FileNameProviderImpl(localizationService(), name);
    }
}
