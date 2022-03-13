package ru.amlet;

import org.springframework.context.annotation.*;
import ru.amlet.service.QuizServiceImpl;

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
