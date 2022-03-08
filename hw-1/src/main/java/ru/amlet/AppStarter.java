package ru.amlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.amlet.service.QuizServiceImpl;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class AppStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppStarter.class);
        context.getBean(QuizServiceImpl.class).conducting();
        context.close();
    }

}
