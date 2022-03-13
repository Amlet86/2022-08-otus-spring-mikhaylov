package ru.amlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.amlet.service.IOServiceImpl;

@Configuration
public class QuizConfiguration {

    @Bean
    public IOServiceImpl IOServiceImpl() {
        return new IOServiceImpl(System.in, System.out);
    }

}
