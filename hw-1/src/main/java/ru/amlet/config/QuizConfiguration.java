package ru.amlet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.amlet.service.IOService;
import ru.amlet.service.IOServiceImpl;

@Configuration
public class QuizConfiguration {

    @Bean
    public IOService IOServiceImpl() {
        return new IOServiceImpl(System.in, System.out);
    }

}
