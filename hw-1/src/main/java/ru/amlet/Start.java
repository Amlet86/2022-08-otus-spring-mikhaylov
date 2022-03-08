package ru.amlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.amlet.service.QuizServiceImpl;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Start {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Start.class);

        context.getBean(QuizServiceImpl.class).conducting();

        context.close();
    }

}
