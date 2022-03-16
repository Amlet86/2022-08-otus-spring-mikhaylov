package ru.amlet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.amlet.service.IOService;
import ru.amlet.service.IOServiceImpl;

import java.util.Locale;

@Configuration
public class QuizConfiguration {

    @Bean
    public IOService ioService() {
        return new IOServiceImpl(System.in, System.out);
    }

    @Bean
    public Locale locale() {
        return new Locale(System.getProperties().getProperty("user.language"));
    }

}
