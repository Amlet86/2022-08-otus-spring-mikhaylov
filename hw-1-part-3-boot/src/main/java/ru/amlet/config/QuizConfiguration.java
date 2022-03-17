package ru.amlet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.amlet.service.IOService;
import ru.amlet.service.IOServiceImpl;
import ru.amlet.service.LocalizationService;
import ru.amlet.service.LocalizationServiceImpl;

import java.util.Locale;

@Configuration
public class QuizConfiguration {

    @Value("${locale}")
    private String locale;

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }

    @Bean
    public LocalizationService localizationService() {
        return new LocalizationServiceImpl(new Locale(locale));
    }
}
